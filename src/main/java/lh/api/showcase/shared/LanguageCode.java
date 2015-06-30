/*
 * Copyright 2015 Loic Merckel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package lh.api.showcase.shared;

import java.util.HashSet;
import java.util.Set;

public enum LanguageCode {
	XX, DE, EN, ES, FI, FR, HR, HU, IT, JA, KA, KO, MI, NL, PL, PT, RU, SL, JP, KR ;
	
	public static Set<String> toStringSet () {
		final Set<String>  set = new HashSet<String>();
		for (LanguageCode lc : values ()){
			set.add(lc.toString());
		}
		return set ;
	}
}
