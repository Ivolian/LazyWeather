package femade.lazyweather;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


public class MySlidingPaneLayout extends ViewGroup {

    // ViewDragHelper 能侦测到的最小速度，设置大一点它不会太敏感
    private static final int MIN_FLING_VELOCITY = 400;

    // 被滑动的View
    private View mSlideView;

    // 滑动百分比
    private float mSlideOffset;

    // 滑动最大距离
    private int mSlideRange;

    // 这个参数比较难说明
    private boolean mIsUnableToDrag;

    // DOWN 事件产生时的坐标
    private float mInitialMotionX;
    private float mInitialMotionY;

    private final ViewDragHelper mDragHelper;

    public MySlidingPaneLayout(Context context) {
        this(context, null);
    }

    public MySlidingPaneLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySlidingPaneLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        final float density = context.getResources().getDisplayMetrics().density;
        // 初始化 ViewDragHelper
        mDragHelper = ViewDragHelper.create(this, 0.5f, new DragHelperCallback());
        mDragHelper.setMinVelocity(MIN_FLING_VELOCITY * density);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // onMeasure 时鸟事不干
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        // 第二个 child 是被滑动的 View
        mSlideView = getChildAt(1);
        // 滑动最大距离是第一个 View 的宽度
        mSlideRange = getChildAt(0).getWidth();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 基本上也鸟事不干
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.layout(0, 0, child.getMeasuredWidth(), child.getMeasuredHeight());
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);

        if ((mIsUnableToDrag && action != MotionEvent.ACTION_DOWN)) {
            mDragHelper.cancel();
            return false;
        }

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mDragHelper.cancel();
            return false;
        }

        boolean interceptTap = false;

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                mIsUnableToDrag = false;
                final float x = ev.getX();
                final float y = ev.getY();
                mInitialMotionX = x;
                mInitialMotionY = y;

                if (mDragHelper.isViewUnder(mSlideView, (int) x, (int) y) &&
                        isOpen()) {
                    interceptTap = true;
                }
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                final float x = ev.getX();
                final float y = ev.getY();
                final float adx = Math.abs(x - mInitialMotionX);
                final float ady = Math.abs(y - mInitialMotionY);
                final int slop = mDragHelper.getTouchSlop();
                if (adx > slop && ady > adx) {
                    mDragHelper.cancel();
                    mIsUnableToDrag = true;
                    return false;
                }
            }
        }

        final boolean interceptForDrag = mDragHelper.shouldInterceptTouchEvent(ev);

        return interceptForDrag || interceptTap;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        mDragHelper.processTouchEvent(ev);

        final int action = ev.getAction();
        switch (action & MotionEventCompat.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                final float x = ev.getX();
                final float y = ev.getY();
                mInitialMotionX = x;
                mInitialMotionY = y;
                break;
            }

            case MotionEvent.ACTION_UP: {
                if (isOpen()) {
                    final float x = ev.getX();
                    final float y = ev.getY();
                    final float dx = x - mInitialMotionX;
                    final float dy = y - mInitialMotionY;
                    final int slop = mDragHelper.getTouchSlop();
                    if (dx * dx + dy * dy < slop * slop &&
                            mDragHelper.isViewUnder(mSlideView, (int) x, (int) y)) {
                        // Taps close a dimmed open pane.
                        smoothSlideTo(0.f, 0);
                        break;
                    }
                }
                break;
            }
        }
        return true;
    }

    // 使用 DragViewHelper 滑动 View，不细究
    boolean smoothSlideTo(float slideOffset, int velocity) {
        int x = (int) (slideOffset * mSlideRange);
        if (mDragHelper.smoothSlideViewTo(mSlideView, x, mSlideView.getTop())) {
            ViewCompat.postInvalidateOnAnimation(this);
            return true;
        }
        return false;
    }

    // 和使用 Scroller 时很像，不细究
    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    // 完全展开
    boolean isOpen() {
        return mSlideOffset == 1;
    }


    private class DragHelperCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            if (mIsUnableToDrag) {
                return false;
            }
            // 只有第二个 View 可以被拖拽
            return indexOfChild(child) == 1;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            // 拖拽后更新滑动百分比
            mSlideOffset = (float) (left) / mSlideRange;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            // xvel > 0 指的是大于最小速度的速度被侦测到，直接滑到 0 或 1（根据速度正负），类似 fling 的行为
            // 否则就是缓慢拖拽，释放后，超过滑动距离超过 0.5 则滑动到 1，反之滑动到 0。
            int left = 0;
            if (xvel > 0 || (xvel == 0 && mSlideOffset > 0.5f)) {
                left += mSlideRange;
            }
            mDragHelper.settleCapturedViewAt(left, releasedChild.getTop());
            invalidate();
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            // 貌似没什么用，还是按规则写着。。。
            return mSlideRange;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
           // 限制水平拖拽的范围，0 ~ mSlideRange
            return Math.min(Math.max(left, 0), mSlideRange);
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            // Make sure we never move views vertically.
            // This could happen if the child has less height than its parent.
            return child.getTop();
        }

    }

}
