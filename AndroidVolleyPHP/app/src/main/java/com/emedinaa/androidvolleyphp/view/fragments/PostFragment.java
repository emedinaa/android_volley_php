package com.emedinaa.androidvolleyphp.view.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.emedinaa.androidvolleyphp.R;
import com.emedinaa.androidvolleyphp.model.ResponseEntity;
import com.emedinaa.androidvolleyphp.view.listeners.OnHomeListener;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link PostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "PostFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnHomeListener mListener;

    @InjectView(R.id.btnPost)Button btnPost;
    @InjectView(R.id.vLoading)View vLoading;
    @InjectView(R.id.tviResult)TextView tviResult;

    private   RequestQueue queue;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostFragment newInstance(String param1, String param2) {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public PostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_post, container, false);
        ButterKnife.inject(this,v);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnHomeListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        queue = Volley.newRequestQueue(getActivity());

        //events
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post();
            }
        });
    }

    private void post() {

        final List<NameValuePair> params= new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("variablexPost","Hello World Android"));

        String url= getString(R.string.url_test);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,new Response.Listener<String>()
        {

            @Override
            public void onResponse(String response)
            {
                Log.d(TAG,"onResponse "+response);
                Gson gson= new Gson();
                ResponseEntity responseEntity= null;

                try {
                    responseEntity= gson.fromJson(response,ResponseEntity.class);

                    tviResult.setText("success  "+response+"/n"+responseEntity.getData().get(0));

                }catch (Exception e)
                {

                }

                vLoading.setVisibility(View.GONE);

            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                vLoading.setVisibility(View.GONE);
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> nParams = new HashMap<String, String>();
                for (int i = 0; i <params.size() ; i++)
                {
                    nParams.put(params.get(i).getName(), params.get(i).getValue());
                }
                Log.d(TAG, "POST params " + nParams.toString());
                return nParams;
            }

        };

        queue.add(stringRequest);
        vLoading.setVisibility(View.VISIBLE);
    }
}
