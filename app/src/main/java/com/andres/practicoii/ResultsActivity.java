package com.andres.practicoii;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.andres.practicoii.model.Vote;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ResultsActivity extends AppCompatActivity {

    FirebaseDatabase database;
    ArrayList<Vote> votes;

    Spinner groupType;
    TextView tvResults;
    String type = "Todo el publico";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        database = FirebaseDatabase.getInstance();
        votes = new ArrayList<>();

        tvResults = findViewById(R.id.tv_results);

        showResults();

        groupType = findViewById(R.id.s_group);
        groupType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type = adapterView.getSelectedItem().toString();
                //Toast.makeText(getApplicationContext(), "tipo: " + type, Toast.LENGTH_LONG).show();
                showResults();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void showResults() {
        database.getReference().child("votes")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        votes = new ArrayList<>();
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            Vote vote = child.getValue(Vote.class);
                            votes.add(vote);
                        }
                        String results = stats();
                        tvResults.setText(results);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public String stats() {
        String result = "";
        int spiderman = 0, ironman = 0, capitan = 0, capitana = 0, blackwidow = 0, hulk = 0, thor = 0, strange = 0;

        switch (type) {
            case "Todo el publico":
                for (Vote vote : votes) {
                    switch (vote.getAvenger()) {
                        case SurveyActivity.IRONMAN:
                            ironman++;
                            break;
                        case SurveyActivity.SPIDERMAN:
                            spiderman++;
                            break;
                        case SurveyActivity.CAPITAN_AMERICA:
                            capitan++;
                            break;
                        case SurveyActivity.CAPITANA_MARVEL:
                            capitana++;
                            break;
                        case SurveyActivity.VIUDA_NEGRA:
                            blackwidow++;
                            break;
                        case SurveyActivity.HULK:
                            hulk++;
                            break;
                        case SurveyActivity.THOR:
                            thor++;
                            break;
                        case SurveyActivity.DOCTOR_STRANGE:
                            strange++;
                            break;
                    }
                }
                break;
            case "Mujeres adultas":
                for (Vote vote : votes) {
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(vote.getBirthDate());
                    int age = age(calendar);
                    if (vote.getGenre().equals("Mujer") && age >= 18) {
                        switch (vote.getAvenger()) {
                            case SurveyActivity.IRONMAN:
                                ironman++;
                                break;
                            case SurveyActivity.SPIDERMAN:
                                spiderman++;
                                break;
                            case SurveyActivity.CAPITAN_AMERICA:
                                capitan++;
                                break;
                            case SurveyActivity.CAPITANA_MARVEL:
                                capitana++;
                                break;
                            case SurveyActivity.VIUDA_NEGRA:
                                blackwidow++;
                                break;
                            case SurveyActivity.HULK:
                                hulk++;
                                break;
                            case SurveyActivity.THOR:
                                thor++;
                                break;
                            case SurveyActivity.DOCTOR_STRANGE:
                                strange++;
                                break;
                        }
                    }
                }
                break;
            case "Hombres adultos":
                for (Vote vote : votes) {
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(vote.getBirthDate());
                    int age = age(calendar);
                    if (vote.getGenre().equals("Hombre") && age >= 18) {
                        switch (vote.getAvenger()) {
                            case SurveyActivity.IRONMAN:
                                ironman++;
                                break;
                            case SurveyActivity.SPIDERMAN:
                                spiderman++;
                                break;
                            case SurveyActivity.CAPITAN_AMERICA:
                                capitan++;
                                break;
                            case SurveyActivity.CAPITANA_MARVEL:
                                capitana++;
                                break;
                            case SurveyActivity.VIUDA_NEGRA:
                                blackwidow++;
                                break;
                            case SurveyActivity.HULK:
                                hulk++;
                                break;
                            case SurveyActivity.THOR:
                                thor++;
                                break;
                            case SurveyActivity.DOCTOR_STRANGE:
                                strange++;
                                break;
                        }
                    }
                }
                break;
            case "Mujeres adolescentes":
                for (Vote vote : votes) {
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(vote.getBirthDate());
                    int age = age(calendar);
                    if (vote.getGenre().equals("Mujer") && age >= 12 && age < 18) {
                        switch (vote.getAvenger()) {
                            case SurveyActivity.IRONMAN:
                                ironman++;
                                break;
                            case SurveyActivity.SPIDERMAN:
                                spiderman++;
                                break;
                            case SurveyActivity.CAPITAN_AMERICA:
                                capitan++;
                                break;
                            case SurveyActivity.CAPITANA_MARVEL:
                                capitana++;
                                break;
                            case SurveyActivity.VIUDA_NEGRA:
                                blackwidow++;
                                break;
                            case SurveyActivity.HULK:
                                hulk++;
                                break;
                            case SurveyActivity.THOR:
                                thor++;
                                break;
                            case SurveyActivity.DOCTOR_STRANGE:
                                strange++;
                                break;
                        }
                    }
                }
                break;
            case "Hombres adolescentes":
                for (Vote vote : votes) {
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(vote.getBirthDate());
                    int age = age(calendar);
                    if (vote.getGenre().equals("Hombre") && age >= 12 && age < 18) {
                        switch (vote.getAvenger()) {
                            case SurveyActivity.IRONMAN:
                                ironman++;
                                break;
                            case SurveyActivity.SPIDERMAN:
                                spiderman++;
                                break;
                            case SurveyActivity.CAPITAN_AMERICA:
                                capitan++;
                                break;
                            case SurveyActivity.CAPITANA_MARVEL:
                                capitana++;
                                break;
                            case SurveyActivity.VIUDA_NEGRA:
                                blackwidow++;
                                break;
                            case SurveyActivity.HULK:
                                hulk++;
                                break;
                            case SurveyActivity.THOR:
                                thor++;
                                break;
                            case SurveyActivity.DOCTOR_STRANGE:
                                strange++;
                                break;
                        }
                    }
                }
                break;
            case "Niños":
                for (Vote vote : votes) {
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(vote.getBirthDate());
                    int age = age(calendar);
                    if (vote.getGenre().equals("Hombre") && age < 12) {
                        switch (vote.getAvenger()) {
                            case SurveyActivity.IRONMAN:
                                ironman++;
                                break;
                            case SurveyActivity.SPIDERMAN:
                                spiderman++;
                                break;
                            case SurveyActivity.CAPITAN_AMERICA:
                                capitan++;
                                break;
                            case SurveyActivity.CAPITANA_MARVEL:
                                capitana++;
                                break;
                            case SurveyActivity.VIUDA_NEGRA:
                                blackwidow++;
                                break;
                            case SurveyActivity.HULK:
                                hulk++;
                                break;
                            case SurveyActivity.THOR:
                                thor++;
                                break;
                            case SurveyActivity.DOCTOR_STRANGE:
                                strange++;
                                break;
                        }
                    }
                }
                break;
        }

        int t = spiderman + ironman + capitan + capitana + blackwidow + hulk + thor + strange;
        double total = (double) t;

        double pIronman = fix((ironman * 100 / total), 2);
        double pSpiderman = fix((spiderman * 100 / total), 2);
        double pCapitan = fix((capitan * 100 / total), 2);
        double pCapitana = fix((capitana * 100 / total), 2);
        double pBlackwidow = fix((blackwidow * 100 / total), 2);
        double pHulk = fix((hulk * 100 / total), 2);
        double pThor = fix((thor * 100 / total), 2);
        double pStrange = fix((strange * 100 / total), 2);

        Toast.makeText(getApplicationContext(), "Total " + t, Toast.LENGTH_LONG).show();

        result += SurveyActivity.IRONMAN + " - Votos: " + ironman + " (" + pIronman + "%)\n";
        result += SurveyActivity.SPIDERMAN + " - Votos: " + spiderman + " (" + pSpiderman + "%)\n";
        result += SurveyActivity.CAPITAN_AMERICA + " - Votos: " + capitan + " (" + pCapitan + "%)\n";
        result += SurveyActivity.CAPITANA_MARVEL + " - Votos: " + capitana + " (" + pCapitana + "%)\n";
        result += SurveyActivity.VIUDA_NEGRA + " - Votos: " + blackwidow + " (" + pBlackwidow + "%)\n";
        result += SurveyActivity.HULK + " - Votos: " + hulk + " (" + pHulk + "%)\n";
        result += SurveyActivity.THOR + " - Votos: " + thor + " (" + pThor + "%)\n";
        result += SurveyActivity.DOCTOR_STRANGE + " - Votos: " + strange + " (" + pStrange + "%)\n";

        return result;
    }

    public double fix(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado = (resultado - parteEntera) * Math.pow(10, numeroDecimales);
        resultado = Math.round(resultado);
        resultado = (resultado / Math.pow(10, numeroDecimales)) + parteEntera;
        return resultado;
    }

    public int age(Calendar fechaNac) {
        Calendar fechaActual = Calendar.getInstance();

        // Cálculo de las diferencias.
        int years = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
        int months = fechaActual.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
        int days = fechaActual.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);

        // Hay que comprobar si el día de su cumpleaños es posterior
        // a la fecha actual, para restar 1 a la diferencia de años,
        // pues aún no ha sido su cumpleaños.

        if (months < 0 // Aún no es el mes de su cumpleaños
                || (months == 0 && days < 0)) { // o es el mes pero no ha llegado el día.
            years--;
        }
        return years;
    }

}
