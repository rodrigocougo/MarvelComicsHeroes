package marvelcomicsheroes.com.classes;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

public class listaDiffCallBack extends DiffUtil.Callback {

    MarvelHeroes mOldLista;
    MarvelHeroes mNewLista;

    public listaDiffCallBack(MarvelHeroes olddbMENSAGEIRO_CHATList, MarvelHeroes newdbMENSAGEIRO_CHATList) {
        this.mOldLista = olddbMENSAGEIRO_CHATList;
        this.mNewLista = newdbMENSAGEIRO_CHATList;
    }

    @Override
    public int getOldListSize() {
        return mOldLista != null ? mOldLista.data.results.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return mNewLista != null ? mNewLista.data.results.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldLista.data.results.get(oldItemPosition).id ==
                mNewLista.data.results.get(newItemPosition).id;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

        return mOldLista.data.results.get(oldItemPosition).equals(mNewLista.data.results.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
