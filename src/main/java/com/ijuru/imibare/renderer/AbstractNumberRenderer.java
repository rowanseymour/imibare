package com.ijuru.imibare.renderer;

import com.ijuru.imibare.lang.NounClassification;

/**
 * Abstract base class for number renderers
 */
public abstract class AbstractNumberRenderer implements NumberRenderer {

	/**
	 * @see NumberRenderer#render(long, com.ijuru.imibare.lang.NounClassification)
	 */
	public String render(long number, NounClassification classification) {
		if (classification != null && !getSupportedNounClassifications().contains(classification)) {
			throw new IllegalArgumentException("Unsupported noun classification for this number renderer");
		}

		if (number == 0) {
			return getZeroWord(classification);
		}

		return renderInternal(number, classification);
	}

	/**
	 * Performs internal rendering of the given number
	 * @param number the number
	 * @param classification the noun classification
	 * @return the rendered form
	 */
	protected abstract String renderInternal(long number, NounClassification classification);

	/**
	 * Gets the word for zero
	 * @param classification the noun classification
	 * @return the word
	 */
	protected abstract String getZeroWord(NounClassification classification);
}