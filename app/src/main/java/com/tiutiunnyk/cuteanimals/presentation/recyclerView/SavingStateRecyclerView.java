package com.tiutiunnyk.cuteanimals.presentation.recyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


public final class SavingStateRecyclerView extends RecyclerView {

    private static final String SAVE_SUPER_STATE = "save_super_state";
    private static final String SAVE_LAYOUT_MANAGER = "save_layout_manager_state";

    private Parcelable layoutSavedState;

    public SavingStateRecyclerView(Context context) {
        super(context);
    }

    public SavingStateRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SavingStateRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public Parcelable getLayoutSavedState() {
        return layoutSavedState;
    }

    public void setLayoutSavedState(Parcelable layoutSavedState) {
        if (layoutSavedState != null) {
            this.layoutSavedState = layoutSavedState;
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(SAVE_SUPER_STATE, super.onSaveInstanceState());
        if (this.getLayoutManager() != null) {
            bundle.putParcelable(SAVE_LAYOUT_MANAGER, this.getLayoutManager().onSaveInstanceState());
        }
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            layoutSavedState = bundle.getParcelable(SAVE_LAYOUT_MANAGER);
            state = bundle.getParcelable(SAVE_SUPER_STATE);
        }
        super.onRestoreInstanceState(state);
    }

    private void restorePosition() {
        if (layoutSavedState != null && this.getLayoutManager() != null) {
            this.getLayoutManager().onRestoreInstanceState(layoutSavedState);
            layoutSavedState = null;
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        restorePosition();
    }
}
