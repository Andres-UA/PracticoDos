package com.andres.practicoii;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.andres.practicoii.model.Vote;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SurveyActivity extends AppCompatActivity {


    public static final String SPIDERMAN = "Spiderman";
    public static final String IRONMAN = "Ironman";
    public static final String CAPITAN_AMERICA = "Capitán América";
    public static final String CAPITANA_MARVEL = "Capitana Marvel";
    public static final String HULK = "Hulk";
    public static final String VIUDA_NEGRA = "Viuda Negra";
    public static final String THOR = "Thor";
    public static final String DOCTOR_STRANGE = "Doctor Strange";

    private Spinner sGenre;
    private EditText etBirthDate;

    private RadioGroup options;

    private String avenger;

    private ImageButton btnShowCalendar;
    private Button btnSubmit;

    private static final String CERO = "0";
    private static final String BARRA = "/";
    public final Calendar c = Calendar.getInstance();

    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        database = FirebaseDatabase.getInstance();

        etBirthDate = findViewById(R.id.et_birth_date);
        sGenre = findViewById(R.id.s_genre);

        etBirthDate.setSelected(false);

        btnShowCalendar = findViewById(R.id.btn_show_birth_date);
        btnShowCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate();
            }
        });

        options = findViewById(R.id.rg_options);

        options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_spiderman) {
                    avenger = SPIDERMAN;
                } else if (checkedId == R.id.rb_ironman) {
                    avenger = IRONMAN;
                } else if (checkedId == R.id.rb_capitan) {
                    avenger = CAPITAN_AMERICA;
                } else if (checkedId == R.id.rb_capitana) {
                    avenger = CAPITANA_MARVEL;
                } else if (checkedId == R.id.rb_hulk) {
                    avenger = HULK;
                } else if (checkedId == R.id.rb_blackwidow) {
                    avenger = VIUDA_NEGRA;
                } else if (checkedId == R.id.rb_thor) {
                    avenger = THOR;
                } else if (checkedId == R.id.rb_strange) {
                    avenger = DOCTOR_STRANGE;
                }
            }
        });

        btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate() == true) {

                    String birthDate = etBirthDate.getText().toString();
                    String genre = sGenre.getSelectedItem().toString();
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        date = sdf.parse(birthDate);
                    } catch (ParseException ex) {
                        Log.v("Exception", ex.getLocalizedMessage());
                    }
                    Vote vote = new Vote(date, avenger, genre);

                    database.getReference().child("votes").push().setValue(vote).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(), "Voto registrado!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SurveyActivity.this, ResultsActivity.class);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Error! " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

    }

    private void getDate() {
        final int mes = c.get(Calendar.MONTH);
        final int dia = c.get(Calendar.DAY_OF_MONTH);
        final int anio = c.get(Calendar.YEAR);

        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10) ? CERO + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);

                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10) ? CERO + String.valueOf(mesActual) : String.valueOf(mesActual);

                etBirthDate.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
            }

        }, anio, mes, dia);
        recogerFecha.show();
    }

    public boolean validate() {

        boolean valid = true;

        String birthDate = etBirthDate.getText().toString();
        String genre = sGenre.getSelectedItem().toString();

        if (avenger.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Debe elegir un vengador.", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        if (birthDate.isEmpty()) {
            etBirthDate.setError("Este campo es obligatorio.");
            valid = false;
        }

        if (genre.isEmpty() || genre.equals("Te identificas como")) {
            Toast.makeText(getApplicationContext(), "Debe elegir un genero.", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }

}
