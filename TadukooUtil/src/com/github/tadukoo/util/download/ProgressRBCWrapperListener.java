package com.github.tadukoo.util.download;

/**
 * Progress RBC Wrapper Listener is listening for updates to a {@link ProgressReadableByteChannelWrapper} in order
 * to keep track of progress updates (e.g. to show a user current progress).
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.5
 */
public interface ProgressRBCWrapperListener{
	
	/**
	 * This will be called by a {@link ProgressReadableByteChannelWrapper} as progress is made with it,
	 * so that this listener can handle the progress update tracking as desired
	 *
	 * @param progress The current progress
	 * @param readSoFar The amount of bytes read so far
	 * @param expectedSize The expected total amount of bytes to be read
	 */
	void progressUpdate(double progress, long readSoFar, long expectedSize);
}
