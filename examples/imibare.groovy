/**
 * Copyright 2011 Rowan Seymour
 * 
 * This file is part of Imibare.
 *
 * Imibare is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Imibare is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Imibare. If not, see <http://www.gnu.org/licenses/>.
 */

package com.ijuru.kumva

import java.util.Locale
import com.ijuru.imibare.NumberRendererFactory
import com.ijuru.imibare.UnsupportedLanguageException
import com.ijuru.imibare.lang.NounAttributes
import com.ijuru.imibare.renderer.NumberRenderer

/**
 * Console based groovy app which uses imibare library to render numbers. For example, to
 * render the number 123 for a Kinyarwanda class 2 noun, run:
 *
 * groovy -cp imibare-1.0.jar imibare.groovy rw 123 c2
 */
public class Imibare {

	/**
	 * Program main method
	 * @param args the console arguments
	 */
	public static void main(String[] args) {
		// Print usage message if we have wrong number of arguments
		if (args.length != 2 && args.length != 3) {
			System.out.println("Usage: imibare <lang> <number> [attributes]")
			return
		}
		
		try {
			// Get program arguments
			String lang = args[0]
			long val = Long.parseLong(args[1])
			NounAttributes attributes = (args.length == 3) ? NounAttributes.parse(args[2]) : new NounAttributes()
			
			try {
				// Select a renderer based on the specified language
				NumberRenderer renderer = NumberRendererFactory.getRendererByLocale(new Locale(lang))
				
				// Calculate the result and print it
				System.out.println(renderer.render(val, attributes))
				
			} catch (UnsupportedLanguageException e) {
				System.out.println("Error: unsupported language")
				return
			}	
		}
		catch (NumberFormatException ex) {
			System.out.println("Error: invalid number")
			return
		}
	}
}