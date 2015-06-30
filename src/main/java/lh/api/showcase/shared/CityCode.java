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

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CityCode implements Serializable {

	private static final long serialVersionUID = 1L;

	private CityCodeAF cityCodeAF = null ;
	private CityCodeGL cityCodeGL = null ;
	private CityCodeMR cityCodeMR = null ;
	private CityCodeSZ cityCodeSZ = null ;
	
	private CityCode () {
		super () ;
		
		// this constructor is for the serializer...
	}
	
	private CityCode (String cc) {
		super () ;
		if (cc == null || cc.isEmpty()) {
			throw new IllegalArgumentException () ;
		}
		if (cc.compareTo(CityCodeGL.GAA.toString()) < 0) {
			cityCodeAF = CityCodeAF.valueOf(cc) ;
			cityCodeGL = null ;	
			cityCodeMR = null ;
			cityCodeSZ = null ;
		} else if (cc.compareTo(CityCodeMR.MAA.toString()) < 0) {
			cityCodeAF = null ;
			cityCodeGL = CityCodeGL.valueOf(cc) ;	
			cityCodeMR = null ;
			cityCodeSZ = null ;
		} else if (cc.compareTo(CityCodeSZ.SAA.toString()) < 0) {
			cityCodeAF = null ;
			cityCodeGL = null ;	
			cityCodeMR = CityCodeMR.valueOf(cc) ;
			cityCodeSZ = null ;
		} else {
			cityCodeAF = null ;
			cityCodeGL = null ;	
			cityCodeMR = null ;
			cityCodeSZ = CityCodeSZ.valueOf(cc) ;
		}
	}
	
	public static CityCode valueOf (String str) {
		return new CityCode (str) ;
	}
	
	@Override
	public String toString() {
		return (cityCodeAF != null) ? (cityCodeAF.toString())
				: ((cityCodeGL != null) ? (cityCodeGL.toString())
						: ((cityCodeMR != null) ? (cityCodeMR.toString())
								: (cityCodeSZ.toString())));
	}

	public static Set<String> toStringSet () {
		final Set<String>  set = new HashSet<String>();
		set.addAll(CityCodeAF.toStringSet()) ;
		set.addAll(CityCodeGL.toStringSet()) ;
		set.addAll(CityCodeMR.toStringSet()) ;
		set.addAll(CityCodeSZ.toStringSet()) ;
		return set ;
	}
}
