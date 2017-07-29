package com.techclutch.finassist.loanstatus;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techclutch.finassist.R;
import com.techclutch.finassist.dummy.DummyContent;
import com.techclutch.finassist.dummy.DummyContent.DummyItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class LoanStatusFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LoanStatusFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static LoanStatusFragment newInstance(int columnCount) {
        LoanStatusFragment fragment = new LoanStatusFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loanstatus_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new LoanStatusRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
    }

//    private List<LoanTypeItem> getBankList() {
//        String[] bankName = getResources().getStringArray(R.array.bank_list);
//        String[] interestRate = getResources().getStringArray(R.array.bank_list_interest_rates);
//        int[] images = getResources().getIntArray(R.array.bank_list_images);
//        TypedArray icons = getResources().obtainTypedArray(R.array.bank_list_images);
//        List<LoanTypeItem> items = new ArrayList<>();
//
//        //todo this is mock data
//        List<String> monthlyPayment = Arrays.asList("500", "400", "300", "500", "400");
//        List<String> totalPayment = Arrays.asList("5055", "40066", "30250", "55200", "40110");
//
//
//        for(int i = 0; i < images.length; ++i) {
//            items.add(new BankTypeItem(bankName[i], interestRate[i], monthlyPayment.get(i), totalPayment.get(i), icons.getResourceId(i, 0));
//        }
//        icons.recycle();
//        return items;
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
