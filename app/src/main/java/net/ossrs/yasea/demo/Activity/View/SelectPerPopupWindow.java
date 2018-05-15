package net.ossrs.yasea.demo.Activity.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import net.ossrs.yasea.demo.R;

public class SelectPerPopupWindow extends PopupWindow
{

    private Button original_filter, beauty_filter, cool_filter,
            early_bird_filter,evergreen_filter,n1977_filter,
            nostalgia_filter,romance_filter,per_qx;
    private View mMenuView;

    @SuppressLint("InflateParams")
    public SelectPerPopupWindow(Context context, View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.perdialog, null);
        original_filter = (Button) mMenuView.findViewById(R.id.original_filter);
        beauty_filter = (Button) mMenuView.findViewById(R.id.beauty_filter);
        cool_filter = (Button) mMenuView.findViewById(R.id.cool_filter);
        early_bird_filter = (Button) mMenuView.findViewById(R.id.early_bird_filter);
        evergreen_filter = (Button) mMenuView.findViewById(R.id.evergreen_filter);
        n1977_filter = (Button) mMenuView.findViewById(R.id.n1977_filter);
        nostalgia_filter = (Button) mMenuView.findViewById(R.id.nostalgia_filter);
        romance_filter = (Button) mMenuView.findViewById(R.id.romance_filter);
        per_qx = (Button) mMenuView.findViewById(R.id.per_qx);

        original_filter.setOnClickListener(itemsOnClick);
        beauty_filter.setOnClickListener(itemsOnClick);
        cool_filter.setOnClickListener(itemsOnClick);

        early_bird_filter.setOnClickListener(itemsOnClick);
        evergreen_filter.setOnClickListener(itemsOnClick);
        n1977_filter.setOnClickListener(itemsOnClick);

        nostalgia_filter.setOnClickListener(itemsOnClick);
        romance_filter.setOnClickListener(itemsOnClick);
        per_qx.setOnClickListener(itemsOnClick);

        this.setContentView(mMenuView);

        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);

        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        this.setFocusable(true);


        ColorDrawable dw = new ColorDrawable(0x80000000);

        this.setBackgroundDrawable(dw);

        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }
}
