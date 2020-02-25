package com.micropos.boletaelectronica.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.micropos.boletaelectronica.R;

import java.util.List;

//Clase que sirve de comunicación entre la vista y los datos. Es utilizada para mostrar la lista
// de dispositivos Bluetooth encontrados
public class AdapterListaDispositivos extends RecyclerView.Adapter<AdapterListaDispositivos.ViewHolderLista> {

    private List<String> mDataSet;
    private OnClickTextViewListener mOnClickTextViewListener;

    public static class ViewHolderLista extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        OnClickTextViewListener onClickTextViewListener;
        public TextView tv;

        public ViewHolderLista(@NonNull TextView tv, OnClickTextViewListener onClickTextViewListener) {
            super(tv);
            this.tv = tv;
            tv.setOnClickListener(this);
            this.onClickTextViewListener = onClickTextViewListener;
        }

        @Override
        public void onClick(View view) {
            onClickTextViewListener.onClickTextView(tv.getText());
        }
    }

    public AdapterListaDispositivos(List mDataSet, OnClickTextViewListener onClickTextViewListener) {
        this.mDataSet = mDataSet;
        this.mOnClickTextViewListener = onClickTextViewListener;
    }

    @NonNull
    @Override
    public ViewHolderLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_recycler_view, parent, false);
        ViewHolderLista vhLista = new ViewHolderLista(tv, mOnClickTextViewListener);
        return vhLista;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLista holder, int position) {
        holder.tv.setText(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    //Interfaz que permite obtener el TextView clickeado
    public interface OnClickTextViewListener {
        void onClickTextView(CharSequence tvText);
    }
}
