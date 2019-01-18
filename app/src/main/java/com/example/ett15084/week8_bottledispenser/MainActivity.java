package com.example.ett15084.week8_bottledispenser;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.negateExact;
import static java.lang.Math.toIntExact;

public class MainActivity extends AppCompatActivity {

    EditText text;
    Context context = null;
    private static SeekBar seek_bar;
    private static TextView dispMoney1;
    BottleDispenser bd = BottleDispenser.getInstance();
    int value;
    Spinner spinner;
    Object selectedItem;
    int pick = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        dispMoney1 = findViewById(R.id.dispMoney);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("\t*** SODA-MACHINE ***\n\n\tChoose your drink\n\tfrom the list above!");

        seekBar();
        setSpinner();

        }

        public void setSpinner(){

            spinner = findViewById(R.id.spinner);
            ArrayAdapter<Bottle> adp = new ArrayAdapter<Bottle>(this,android.R.layout.simple_spinner_item, bd.bottles_array);
            adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adp);

            spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener(){

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                {
                    //Bottle bottle = (Bottle) parent.getSelectedItem();

                    pick = toIntExact(id);
                    System.out.println("Pick main aktivitissa: " + pick);
                    dispList();

                } // to close the onItemSelected

                @Override
                public void onNothingSelected(AdapterView<?> parent)
                {
                    TextView textView = findViewById(R.id.textView);
                    textView.setText("Dispenser empty");
                }
            });

        }

        private void dispList(){
        String type = bd.bottles_array.get(pick).getType();
        float amount = bd.bottles_array.get(pick).getAmount();
        float price = bd.bottles_array.get(pick).getPrice();

        String bottleType = "Type: " + type + "\nAmount: " + amount + "\nPrice: " + price;

        Toast.makeText(this, bottleType, Toast.LENGTH_LONG).show();

        }


        public void seekBar(){
            seek_bar = findViewById(R.id.seekBar);
            seek_bar.setMax(4);

            seek_bar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        value = progress;
                        dispMoney1.setText(progress + "/" + seek_bar.getMax() + "€");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }

            });

        }


        public void addMoney(View v){

            TextView textView = findViewById(R.id.textView);
            textView.setText("Rahaa laitettu, yhteensä " + bd.addMoney(value) + "€");
            //System.out.println(bottles_array);

        }

        public void returnMoney(View v){
            TextView textView = findViewById(R.id.textView);
            textView.setText( bd.returnMoney());


            // Litania saa aikaan sekunnin viiveen
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 1s = 1000ms
                    TextView textView = findViewById(R.id.textView);
                    textView.setText("\t*** SODA-MACHINE ***\n\n\tChoose your drink\n\tfrom the list above!");
                }
            }, 1000);

        }


        public void buyBottle(View v) {
            TextView textView = findViewById(R.id.textView);

            if(pick == -1){
                textView.setText("Valitse pullo!");
            }
            else {
                textView.setText(bd.buyBottle(pick));
                pick = -1; // Oston jälkeen asetetaan pick takaisin -1 jolloin tiedetään koska pullot on loppu
                System.out.println("#######################################Piccki palautettu miinus ykköseksi");
            }
        }



        /*while(true){
            System.out.println("\n*** LIMSA-AUTOMAATTI ***");
            System.out.println("1) Lisää rahaa koneeseen");
            System.out.println("2) Osta pullo");
            System.out.println("3) Ota rahat ulos");
            System.out.println("4) Listaa koneessa olevat pullot");
            System.out.println("0) Lopeta");

            System.out.print("Valintasi: ");
            Scanner scan = new Scanner(System.in);
            String choice = scan.nextLine();

            if("1".equals(choice)){
                bd.addMoney();
                //System.out.println("\n");

            }
            else if("2".equals(choice)){
                bd.listProducts();
                System.out.print("Valintasi: ");
                Scanner scan1 = new Scanner(System.in);
                String pick = scan1.nextLine();
                bd.buyBottle(pick);
                //System.out.println("\n");

            }
            else if("3".equals(choice)){
                bd.returnMoney();
                //System.out.println("\n");

            }
            else if("4".equals(choice)){
                bd.listProducts();
                //System.out.println("\n");

            }
            else if("0".equals(choice)){
                break;
            }

            else{
                System.out.println("Valitse uudestaan!");
                //System.out.println("\n");

            }
        }*/
    }


