# CustomAsyncTask
An approach that maintains a similar structure to the already deprecated AsyncTask while using modern concurrency tools provided by the Java standard library.
Usage, declare your class:
```java
public class MyTask extends CustomAsyncTask<String, Integer, String> {

    @Override
    protected void onPreExecute() {
        // Code to execute before the background task starts
    }

    @Override
    protected String doInBackground(String... params) {
        // Background task logic
        // Example of publishing progress:

        return "Task Completed!";
    }

    @Override
    protected void onPostExecute(String result) {
        // Code to execute after the background task is completed
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        // Code to update UI with progress
    }
}
```
Then execute:
```java
new MyTask().execute("param1", "param2");
