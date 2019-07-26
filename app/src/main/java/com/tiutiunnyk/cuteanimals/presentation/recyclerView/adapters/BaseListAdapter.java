package com.tiutiunnyk.cuteanimals.presentation.recyclerView.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tiutiunnyk.cuteanimals.presentation.recyclerView.adapters.holders.BaseViewHolder;
import com.tiutiunnyk.cuteanimals.presentation.recyclerView.adapters.listenersContracts.BaseRecyclerListener;

/**
 * @param <T>  type of objects, which will be used in the adapter's dataset
 * @param <L>  click listener {@link BaseRecyclerListener}
 * @param <VH> ViewHolder {@link BaseViewHolder}
 */
public abstract class BaseListAdapter<T, L extends BaseRecyclerListener, VH extends BaseViewHolder<T, L>> extends ListAdapter<T, VH> {
    private L listener;
    private LayoutInflater layoutInflater;

    protected BaseListAdapter(@NonNull Context context,
                              @NonNull L listener,
                              @NonNull DiffUtil.ItemCallback<T> diffCallback) {
        super(diffCallback);
        this.listener = listener;
        this.layoutInflater = LayoutInflater.from(context);
    }

    protected BaseListAdapter(@NonNull Context context,
                              @NonNull L listener,
                              @NonNull AsyncDifferConfig<T> config) {
        super(config);
        this.listener = listener;
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        T item = getItem(position);
        holder.onBind(item, listener);
    }

    @NonNull
    protected View inflate(@LayoutRes final int layout, @Nullable final ViewGroup parent, final boolean attachToRoot) {
        return layoutInflater.inflate(layout, parent, attachToRoot);
    }

    /**
     * Inflates a view.
     *
     * @param layout layout to me inflater
     * @param parent container where to inflate
     * @return inflated View
     */
    @NonNull
    protected View inflate(@LayoutRes final int layout, final @Nullable ViewGroup parent) {
        return inflate(layout, parent, false);
    }
}
