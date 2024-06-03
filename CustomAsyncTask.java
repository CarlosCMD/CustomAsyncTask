import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class CustomAsyncTask<Params, Progress, Result> {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    protected abstract void onPreExecute();
    protected abstract Result doInBackground(Params... params);
    protected abstract void onPostExecute(Result result);
    protected void onProgressUpdate(Progress... values) {}

    public void execute(final Params... params) {
        onPreExecute();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                final Result result = doInBackground(params);
                
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onPostExecute(result);
                    }
                });
            }
        });
    }

    protected void publishProgress(final Progress... values) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onProgressUpdate(values);
            }
        });
    }
}