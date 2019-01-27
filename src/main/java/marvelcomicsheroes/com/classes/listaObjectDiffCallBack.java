//package marvelcomicsheroes.com.classes;
//
//import android.support.annotation.Nullable;
//import android.support.v7.util.DiffUtil;
//
//public class listaObjectDiffCallBack {
//
//    Object mOldListaObject;
//    Object mNewListaObject;
//
//    public listaObjectDiffCallBack(Object oldLista, Object newLista) {
//        this.mOldListaObject = oldLista;
//        this.mNewListaObject = newLista;
//    }
//
//    @Override
//    public int getOldListSize() {
//        if (mDataSetHor instanceof Comics) {
//            return MC_COMICS;
//        } else if (mDataSetHor instanceof Series) {
//            return MC_SERIES;
//        } else if (mDataSetHor instanceof Stories) {
//            return MC_STORIES;
//        } else if (mDataSetHor instanceof Events) {
//            return MC_EVENTS;
//        } else if (mDataSetHor instanceof Url) {
//            return MC_URLS;
//        }
//        return mOldListaObject != null ? mOldListaObject.data.results.size() : 0;
//    }
//
//    @Override
//    public int getNewListSize() {
//        return mNewListaObject != null ? mNewListaObject.data.results.size() : 0;
//    }
//
//    @Override
//    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
//        return mOldListaObject.data.results.get(oldItemPosition).id ==
//                mNewListaObject.data.results.get(newItemPosition).id;
//    }
//
//    @Override
//    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
//
//        return mOldListaObject.data.results.get(oldItemPosition).equals(mNewListaObject.data.results.get(newItemPosition));
//    }
//
//    @Nullable
//    @Override
//    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
//        // Implement method if you're going to use ItemAnimator
//        return super.getChangePayload(oldItemPosition, newItemPosition);
//    }
//}
