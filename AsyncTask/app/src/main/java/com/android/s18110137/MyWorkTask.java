package com.android.s18110137;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;

import com.android.s18110137.databinding.ActivityMainBinding;

// <Params, Progress, Result>
public class MyWorkTask extends AsyncTask<ParamInfo, ProgressInfo, ResultInfo> {

    ActivityMainBinding mainBinding;

    private final int PROGRESS_MAX;
    private int workCount = 0;
    private long startTimeInMillis;

    public MyWorkTask(ActivityMainBinding binding) {
        this.mainBinding = binding;
        this.PROGRESS_MAX = this.mainBinding.progressBar.getMax();
    }

    @Override
    protected void onPreExecute() {
        this.mainBinding.progressBar.setVisibility(ProgressBar.VISIBLE);
        this.mainBinding.textStatus.setText("Napping...");
        this.mainBinding.textInfo.setText("Start Napping...");
        this.mainBinding.btnStart.setEnabled(false);
    }

    @Override
    protected void onPostExecute(ResultInfo resultInfo) {
        super.onPostExecute(resultInfo);
        this.mainBinding.btnStart.setEnabled(true);
        this.mainBinding.textStatus.setText("Working...");
        this.mainBinding.textInfo.setText(resultInfo.getMessage());
    }

    @Override
    protected void onProgressUpdate(ProgressInfo... values) {
        super.onProgressUpdate(values);
        ProgressInfo progressInfo = values[0];

        int progress = progressInfo.getProgress();

        this.mainBinding.progressBar.setProgress(progress);
        this.mainBinding.textInfo.setText(progressInfo.getWorkingInfo());
    }

    @Override
    protected ResultInfo doInBackground(ParamInfo... paramInfos) {
        final int WORK_MAX = 60;

        while (this.workCount < WORK_MAX) {
            SystemClock.sleep(1000);
            this.workCount++;

            int progress = (this.workCount * PROGRESS_MAX) / WORK_MAX; // Progress value.
            int percent = (progress * 100) / PROGRESS_MAX;
            String info = "(" + percent +"%) - Slept " + this.workCount + " of " + WORK_MAX + " seconds";

            ProgressInfo progressInfo = new ProgressInfo(progress, info);
            this.publishProgress(progressInfo); // Progress ...values
        }

        ResultInfo result = new ResultInfo(true);

        return result;
    }
}