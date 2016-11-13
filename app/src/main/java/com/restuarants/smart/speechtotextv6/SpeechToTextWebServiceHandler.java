package com.restuarants.smart.speechtotextv6;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;


import com.ibm.watson.developer_cloud.speech_to_text.v1.model.Transcript;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class SpeechToTextWebServiceHandler {
    private final String WEBSERVICE_URL = "https://stream.watsonplatform.net/speech-to-text/api/v1/recognize";
    private final String USERNAME = "6cf58e19-2519-4393-82ba-e1a45157dd59";
    private final String PASSWORD = "pEICSfPnLvSq";
    private String type = "POST";
    private Context appContext;
    private File audioFile;
    private String outputText;
    public SpeechToTextWebServiceHandler(Context appContext) {
        this.appContext = appContext;
    }
    public void execute(String outputText) {
        this.outputText = outputText;
        new TheTask().execute(WEBSERVICE_URL);
    }
    class TheTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... arg0) {
            String audio = null;
            try {
                com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText service = new com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText();
                service.setUsernameAndPassword(USERNAME, PASSWORD);

                try {

                    audio = outputText;

                    File downloadsFolder =
                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    audioFile = new File(downloadsFolder, "test.ogg");//File.createTempFile("convertedFile", ".ogg",downloadsFolder);

                    InputStream stream;
                    List<Transcript> list = service.recognize(audioFile/*, RecognizeOptions,
                            SpeechModel.EN_UK_BROADBANDMODEL*/).getResults();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
            return audio;
        }
    }
}
