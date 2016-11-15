package com.inonity.smtpmailsending;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    final String fromEmail = "cloudaps@gmail.com";
    final String fromEmailPass = "Trip123#";
    String toEmail = "ruhul.azam1@gmail.com";
    String emailSubject = "Email using SMTP server";
    String emalBody = "This is an email sent using my Mail JavaMail wrapper from an Android device.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            new SendMail().execute();
        } catch(Exception e) {
            Log.e("MailApp", "Could not send email", e);
        }
    }

    // more of the getters and setters …..
    public class SendMail extends AsyncTask<String, Integer, Void> {

        protected void onProgressUpdate() {
            //called when the background task makes any progress
        }

        @Override
        protected Void doInBackground(String... strings) {
            Mail m = new Mail(fromEmail,fromEmailPass);
            String[] toArr = {toEmail};
            m.setTo(toArr);
            m.setFrom(fromEmail);
            m.setSubject(emailSubject);
            m.setBody(emalBody);
            try {
                 //m.addAttachment("/sdcard/filelocation");

                if(m.send()) {
                    Log.i("Mail sending status","........................................... Email was sent successfully.");
                } else {
                    Log.i("Mail sending status","........................................... Email sending failed.");
                }
            } catch(Exception e) {
                //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show();
                Log.e("MailApp", "Could not send email", e);
            }
            return null;
        }

        protected void onPreExecute() {
            //called before doInBackground() is started
            //m.addAttachment("/sdcard/filelocation");
        }
        protected void onPostExecute() {
            //called after doInBackground() has finished
        }
    }
}
