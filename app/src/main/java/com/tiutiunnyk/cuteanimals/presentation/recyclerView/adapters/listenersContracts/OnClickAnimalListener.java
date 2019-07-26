package com.tiutiunnyk.cuteanimals.presentation.recyclerView.adapters.listenersContracts;


public interface OnClickAnimalListener<T> extends BaseRecyclerListener {

    void onAnimalItemClicked(T item);
}
