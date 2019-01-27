package marvelcomicsheroes.com.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import marvelcomicsheroes.com.ListaItem;
import marvelcomicsheroes.com.MainListaPrincipal;
import marvelcomicsheroes.com.R;
import marvelcomicsheroes.com.classes.MarvelHeroes;

public class adpListaPrincipal extends RecyclerView.Adapter<adpListaPrincipal.ViewHolder> {

    public final MainListaPrincipal mParentActivity;
    public MarvelHeroes mDataSet = null;
    private ProgressBar progressBar;

    public adpListaPrincipal(MainListaPrincipal parent) {
        mParentActivity = parent;
        progressBar = parent.findViewById(R.id.pgbItemLista);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_lista_conteudo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.cslItemListaFundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, ListaItem.class);

                intent.putExtra("marve_herois", mDataSet);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });

        String extension = mDataSet.data.results.get(position).thumbnail.extension;
        String path = mDataSet.data.results.get(position).thumbnail.path;
        Uri uri = Uri.parse(path + "/standard_large." + extension);

        holder.sdvItemListaResourceURI.setImageURI(uri);
        holder.txvItemListaName.setText(mDataSet.data.results.get(position).name);
        holder.txvItemListaDescription.setText(mDataSet.data.results.get(position).description);
    }

    @Override
    public int getItemCount() {
        return mDataSet != null ? mDataSet.data.results.size() : 0 ;
    }

    /* ######################################################################################### */
    /* ViewHolders */
    class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout cslItemListaFundo;
        SimpleDraweeView sdvItemListaResourceURI;
        TextView txvItemListaName;
        TextView txvItemListaDescription;

        ViewHolder(View view) {
            super(view);
            cslItemListaFundo = view.findViewById(R.id.cslItemListaFundo);
            sdvItemListaResourceURI = view.findViewById(R.id.sdvItemListaResourceURI);
            txvItemListaName = view.findViewById(R.id.txvItemListaName);
            txvItemListaDescription = view.findViewById(R.id.txvItemListaDescription);
        }
    }

    /* ######################################################################################### */
    /* UTILs */
    public void addHeroes(MarvelHeroes VFmDataSet){
//        this.mDataSet = VFmDataSet;
        progressBar.setVisibility(View.GONE);
        int cont = getItemCount();
        if(mDataSet != null) {
            for (int x = cont+1; x < VFmDataSet.data.results.size(); x++) {
                this.mDataSet.data.results.add(VFmDataSet.data.results.get(x));
                notifyItemInserted(x);
            }
        }else{
            this.mDataSet = VFmDataSet;
            notifyDataSetChanged();
        }
    }

    public boolean getHeroisQT(){
        return mDataSet != null;
    }

    /* ######################################################################################### */
}
