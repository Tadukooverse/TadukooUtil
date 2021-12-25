package com.github.tadukoo.util.download;

import com.github.tadukoo.util.logger.EasyLogger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/**
 * Download Util contains utilities used for downloading files
 *
 * @author Logan Ferree
 * @version Beta v.0.5.2
 */
public class DownloadUtil{
	
	/** Not allowed to instantiate DownloadUtil */
	private DownloadUtil(){ }
	
	/**
	 * Attempts to grab the file size for the file at the given {@link URL}. If it fails, a warning
	 * will be logged, but it will not error out and return -1
	 *
	 * @param logger The {@link EasyLogger logger} to use for logging if we fail
	 * @param url The {@link URL} the file is located at
	 * @return The size of the file in bytes, or -1 if we fail to retrieve it
	 */
	public static int getFileSize(EasyLogger logger, URL url){
		// Set file size default to -1 (so we can at least return something if it fails)
		int fileLength = -1;
		
		try{
			// Follow redirects for grabbing file size (set in case something else set it to false)
			HttpURLConnection.setFollowRedirects(true);
			
			// Setup a connection to get the HEAD for the file
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("HEAD");
			
			// Grab the file size from the connection
			fileLength = connection.getContentLength();
		}catch(Exception e){
			// Log warning that we couldn't get file size + notify user
			logger.logWarning("Failed to get file size at url: " + url.getPath(), e);
		}
		
		return fileLength;
	}
	
	/**
	 * If the file already exists at the given filepath, nothing happens. Otherwise, we download it from the
	 * given address, and progress will be updated by sending this as a {@link ProgressRBCWrapperListener} to
	 * the {@link ProgressReadableByteChannelWrapper} we use
	 *
	 * @param logger The {@link EasyLogger logger} to use for logging
	 * @param listener The {@link ProgressRBCWrapperListener progress listener} to send updates to
	 * @param address The URL for the file to be downloaded
	 * @param filepath The local filepath for the file
	 * @throws IOException If anything goes wrong in downloading the file
	 */
	public static void downloadFile(
			EasyLogger logger, ProgressRBCWrapperListener listener, String address, String filepath) throws IOException{
		// Check if file already exists so we don't need to download it
		File file = new File(filepath);
		if(file.exists()){
			return;
		}
		
		// Setup the download, including setting this as the listener for progress updates
		URL url = new URL(address);
		// Follow redirects for the file (set in case something else sets it to false)
		HttpURLConnection.setFollowRedirects(true);
		ReadableByteChannel fileDownload = new ProgressReadableByteChannelWrapper(
				Channels.newChannel(url.openStream()), listener, getFileSize(logger, url));
		
		// Perform the file transfer from the URL to our local filepath
		FileOutputStream fileOutputStream = new FileOutputStream(filepath);
		FileChannel fileChannel = fileOutputStream.getChannel();
		fileChannel.transferFrom(fileDownload, 0, Long.MAX_VALUE);
		
		// Close the file channels and output stream now that we're done
		fileDownload.close();
		fileChannel.close();
		fileOutputStream.close();
	}
}
