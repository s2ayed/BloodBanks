package com.example.bloodbank.ui.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.BloodTypeAdapter;
import com.example.bloodbank.adapter.CityAdapter;
import com.example.bloodbank.adapter.GovernortareAdapter;
import com.example.bloodbank.data.api.ApiServer;
import com.example.bloodbank.data.model.BoodTypeSpinnnerModel;
import com.example.bloodbank.data.model.CityGovernorateSpinnnerModel;
import com.example.bloodbank.data.model.blood_types.BloodTypes;
 import com.example.bloodbank.data.model.governorates.Governorates;
import com.example.bloodbank.data.model.register.Register;
import com.example.bloodbank.ui.activity.HomeActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.RetrofitClient.getClient;
import static com.example.bloodbank.helper.HelperMathod.checkCorrespondPassword;
import static com.example.bloodbank.helper.HelperMathod.checkLengthPassword;

public class RegisterFragment extends Fragment {


    Unbinder unbinder;

    @BindView(R.id.registerFragmentEditUserName)
    EditText registerFragmentEditUserName;
    @BindView(R.id.registerFragmentEditEmail)
    EditText registerFragmentEditEmail;
    @BindView(R.id.registerFragmentEditDataOfBirth)
    EditText registerFragmentEditDataOfBirth;
    @BindView(R.id.registerFragmentBloodTypesSpinner)
    Spinner registerFragmentBloodTypesSpinner;
    @BindView(R.id.registerFragmentDataLastDonation)
    EditText registerFragmentDataLastDonation;
    @BindView(R.id.registerFragmentGovernoratesSpinner)
    Spinner registerFragmentGovernoratesSpinner;
    @BindView(R.id.registerFragmentCiteSpinner)
    Spinner registerFragmentCiteSpinner;
    @BindView(R.id.registerFragmentPasswordEdit)
    EditText registerFragmentPasswordEdit;
    @BindView(R.id.registerFragmentEmphasisPasswordEdit)
    EditText registerFragmentEmphasisPasswordEdit;
    @BindView(R.id.registerFragmentPhoneEdit)
    EditText registerFragmentPhoneEdit;
    @BindView(R.id.registerFragmentRegisterBtn)
    Button registerFragmentRegisterBtn;
    @BindView(R.id.redisterFragmentTolBr)
    Toolbar redisterFragmentTolBr;
    ProgressBar progressBar;
    private View view;
    ApiServer apiServer;
    private String DataOfBirth, DataLastDonation;
    // value adapter city
    CityAdapter cityAdapter;
    ArrayList<CityGovernorateSpinnnerModel> spinnnerModelArrayListCity;
    CityGovernorateSpinnnerModel citySpinnnerModel;
    // value adapter governortare
    GovernortareAdapter governortareAdapter;
    ArrayList<CityGovernorateSpinnnerModel> spinnnerModelArrayListGovernortare;
    CityGovernorateSpinnnerModel governorateSpinnnerModel;

    BloodTypeAdapter bloodTypeAdapter;
    ArrayList<BoodTypeSpinnnerModel> boodTypeSpinnnerModelArrayList;
    BoodTypeSpinnnerModel boodTypeSpinnnerModel;
    private int idCity;
    private int idBloodTypes;
    private int idGovernorates;
    private int YEAR;
    private int MONTH;
    private int DAY_OF_MONTH;
    private Calendar calendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_register, container, false);
        //
        inti();
        unbinder = ButterKnife.bind(this, view);
        progressBar.setVisibility(View.INVISIBLE);


        OnClick();
        new getDataCityGovernorte().execute();


        return view;
    }

    @SuppressLint("StaticFieldLeak")
    public class getDataCityGovernorte extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);

        }

        @Override
        protected String doInBackground(String... strings) {
            try {

                // get  Data governorates
                Call<Governorates> governoratesCall = apiServer.getGovernorate();
                governoratesCall.enqueue(new Callback<Governorates>() {
                    @Override
                    public void onResponse(Call<Governorates> call, Response<Governorates> response) {
                        Log.d("response", response.body().getMsg());

                        if (response.body().getStatus() == 1) {

                            spinnnerModelArrayListGovernortare.add(new CityGovernorateSpinnnerModel(0, "المحافظه"));

                            for (int i = 0; i < response.body().getData().size(); i++) {
                                governorateSpinnnerModel = new CityGovernorateSpinnnerModel(response.body().getData().get(i).getId(),
                                        response.body().getData().get(i).getName());
                                spinnnerModelArrayListGovernortare.add(governorateSpinnnerModel);
                            }

                            governortareAdapter = new GovernortareAdapter(getContext(), spinnnerModelArrayListGovernortare);
                            registerFragmentGovernoratesSpinner.setAdapter(governortareAdapter);
                            registerFragmentGovernoratesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if (position != 0) {

                                        idGovernorates = boodTypeSpinnnerModelArrayList.get(position).getId();
                                        // get  Data cities
                                        getDataCity(idGovernorates);
                                    }

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }

                    }

                    @Override
                    public void onFailure(Call<Governorates> call, Throwable t) {

                    }
                });


                // get  Data cities
                Call<BloodTypes> getblood_types = apiServer.getBlood_types();
                getblood_types.enqueue(new Callback<BloodTypes>() {
                    @Override
                    public void onResponse(Call<BloodTypes> call, Response<BloodTypes> response) {
                        Log.d("response BloodTypes", response.body().getMsg());

                        if (response.body().getStatus() == 1) {

                            boodTypeSpinnnerModelArrayList.add(new BoodTypeSpinnnerModel(0, "فصيلة الدم"));

                            for (int i = 0; i < response.body().getData().size(); i++) {

                                boodTypeSpinnnerModel = new BoodTypeSpinnnerModel(response.body().getData().get(i).getId(),
                                        response.body().getData().get(i).getName());

                                boodTypeSpinnnerModelArrayList.add(boodTypeSpinnnerModel);
                            }

                            bloodTypeAdapter = new BloodTypeAdapter(getContext(), boodTypeSpinnnerModelArrayList);
                            registerFragmentBloodTypesSpinner.setAdapter(bloodTypeAdapter);
                            registerFragmentBloodTypesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if (position != 0) {

                                        idBloodTypes = boodTypeSpinnnerModelArrayList.get(position).getId();
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<BloodTypes> call, Throwable t) {

                    }
                });

            } catch (Exception e) {
                e.getMessage();
            }
            return null;
        }
    }

    // get  Data cities
    private void getDataCity(int idGovernorates) {


        Call<Governorates> citiesCall = apiServer.getCities(idGovernorates);
        citiesCall.enqueue(new Callback<Governorates>() {
            @Override
            public void onResponse(Call<Governorates> call, Response<Governorates> response) {

                if (response.body().getStatus() == 1) {

                    spinnnerModelArrayListCity.add(new CityGovernorateSpinnnerModel(0,
                            getResources().getString(R.string.city)));

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        citySpinnnerModel = new CityGovernorateSpinnnerModel(response.body().getData().get(i).getId(),
                                response.body().getData().get(i).getName());

                        spinnnerModelArrayListCity.add(citySpinnnerModel);
                    }

                    cityAdapter = new CityAdapter(getContext(), spinnnerModelArrayListCity);
                    registerFragmentCiteSpinner.setAdapter(cityAdapter);
                    registerFragmentCiteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position != 0) {
                                idCity = boodTypeSpinnnerModelArrayList.get(position).getId();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Governorates> call, Throwable t) {

            }
        });
    }

    private void inti() {

        apiServer = getClient().create(ApiServer.class);
        progressBar = view.findViewById(R.id.progressBar);
        spinnnerModelArrayListGovernortare = new ArrayList<>();
        spinnnerModelArrayListCity = new ArrayList<>();
        boodTypeSpinnnerModelArrayList = new ArrayList<>();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.registerFragmentRegisterBtn)
    public void onViewClicked() {

        if (checkLengthPassword(registerFragmentPasswordEdit.getText().toString())
                && checkCorrespondPassword(registerFragmentPasswordEdit.getText().toString(), registerFragmentEmphasisPasswordEdit.getText().toString())) {
            Call<Register> call = apiServer.onRegister(
                    registerFragmentEditUserName.getText().toString(), registerFragmentEditEmail.getText().toString(),
                    registerFragmentEditDataOfBirth.getText().toString(), idCity,
                    registerFragmentPhoneEdit.getText().toString(), registerFragmentDataLastDonation.getText().toString(),
                    registerFragmentPasswordEdit.getText().toString(), registerFragmentEmphasisPasswordEdit.getText().toString()
                    , idBloodTypes);
            call.enqueue(new Callback<Register>() {

                @Override
                public void onResponse(Call<Register> call, Response<Register> response) {
                    Log.d("response", response.body().getMsg());
                    Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    if (response.body().getStatus() == 1) {

                        Intent intent = new Intent(getContext(), HomeActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Register> call, Throwable t) {

                }
            });
        } else {


            if (!checkLengthPassword(registerFragmentPasswordEdit.getText().toString())) {
                registerFragmentEmphasisPasswordEdit.setError(getResources().getString(R.string.It_should_be_the_largest6));
            }
            if (!checkCorrespondPassword(registerFragmentPasswordEdit.getText().toString(), registerFragmentEmphasisPasswordEdit.getText().toString())) {
                registerFragmentEmphasisPasswordEdit.setError(getResources().getString(R.string.number_does_not_correspond));
            }
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    public void OnClick() {

        DecimalFormat decimalFormat = new DecimalFormat("00");
        calendar = Calendar.getInstance();
        YEAR = Integer.parseInt(decimalFormat.format(calendar.get(Calendar.YEAR)));
        MONTH = Integer.parseInt(decimalFormat.format(calendar.get(Calendar.MONTH)));
        DAY_OF_MONTH = Integer.parseInt(decimalFormat.format(calendar.get(Calendar.DAY_OF_MONTH)));

        DataOfBirth = "1972" + "-" + "01" + "-" +"01";
        registerFragmentEditDataOfBirth.setText(DataOfBirth);
        registerFragmentEditDataOfBirth.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                        @SuppressLint("DefaultLocale")
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            DataOfBirth = year + "-" + String.format("%02d", month) + "-" + String.format("%02d", dayOfMonth);
                            registerFragmentEditDataOfBirth.setText(DataOfBirth);
                        }
                    },YEAR,MONTH, DAY_OF_MONTH);

                    datePickerDialog.show();
                    return true;
                }
                return false;
            }
        });

        registerFragmentDataLastDonation.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Calendar calendar = Calendar.getInstance();
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                        @SuppressLint("DefaultLocale")
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            DataLastDonation = year + "-" +  String.format("%02d", month)  + "-" +String.format("%02d", dayOfMonth);
                            registerFragmentDataLastDonation.setText(DataLastDonation);
                        }
                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                    datePickerDialog.show();

                    return true;
                }
                return false;
            }
        });

    }


}
