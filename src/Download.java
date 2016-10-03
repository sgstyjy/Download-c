import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Download {
	 
		public static void main(String args[]) throws Exception {
			//create multiple threads to download file from server
			//@para 1: the target url
			//@para 2: the number of thread
			
			String url = args[0];
			int MYTHREADS = Integer.parseInt(args[1]);
			
			ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);
			Runnable worker = new MyRunnable(url);
			executor.execute(worker);

			executor.shutdown();
			// Wait until all threads are finish
			while (!executor.isTerminated()) {
	 
			}
			//System.out.println("\nFinished all threads");
		}
	 
		public static class MyRunnable implements Runnable {
			private final String url;
	 
			MyRunnable(String url) {
				this.url = url;
			}
	 
			@Override
			public void run() {
	 
				//String fileName = "download/"+Thread.currentThread().getId()+".txt"; //The file that will be saved on your computer
				 URL link = null;
				try {
					link = new URL(url);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} //The file that you want to download
				
		     //Code to download
				while(true){
					try {
						 InputStream in = new BufferedInputStream(link.openStream());
						 ByteArrayOutputStream out = new ByteArrayOutputStream();
						 byte[] buf = new byte[4096];
						 int n = 0;
						 
							while (-1!=(n=in.read(buf)))
							 {
							    out.write(buf, 0, n);
							 }
							out.close();
							in.close();
							
							/*byte[] response = out.toByteArray();
							FileOutputStream fos = null;
							try {
								fos = new FileOutputStream(fileName);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							fos.write(response);
							fos.close();*/
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}			 
				}
			
			}
		}
}
