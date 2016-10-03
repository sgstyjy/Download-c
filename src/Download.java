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
import java.lang.*;

public class Download {
	 
		public static void main(String args[]) throws Exception {
			//create multiple threads to download file from server
			//@para 1: the target url
			//@para 2: the number of thread
			
			String url = args[0];
			int MYTHREADS = Integer.parseInt(args[1]);
			//System.out.println(MYTHREADS);
			//System.out.println(url);
			
			ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);
			Runnable worker = new MyRunnable(url);
			for(int i=0; i<MYTHREADS; i++){
				executor.execute(worker);
			}
			
			executor.shutdown();
			// Wait until all threads are finish
			while (!executor.isTerminated()) {
			}
			//System.out.println("\nFinished all threads");
		}
	 
		public static class MyRunnable implements Runnable {
			private final String url;
			private URL link;
	 
			public MyRunnable(String url) throws MalformedURLException {
				this.url = url;
				this.link = new URL(this.url);
			}
	 
			@Override
			public void run() {
	 
				//String fileName = "~/myfile/"+Thread.currentThread().getId()+".txt"; //The file that will be saved on your computer
				//String fileName = "/13.txt"; 
				System.out.println(Thread.currentThread().getId()+"  "+Thread.currentThread().getName());
				
		        //Code to download
				//while(true){
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
							fos.flush();
							fos.close();*/
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					//System.out.println(Thread.currentThread().getName()+"\n");
				//}
			
			}
		}
}
