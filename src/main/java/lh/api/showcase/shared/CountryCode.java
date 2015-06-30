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

public enum CountryCode {
	
	AE, AF, AG, AI, AL, AM, AN, AO, AQ, AR, AS, AT, AU, AW, 
	AZ, BA, BB, BD, BE, BF, BG, BH, BI, BJ, BM, BN, BO, BR, BS, 
	BT, BW, BY, BZ, CA, CC, CD, CF, CG, CH, CI, CK, CL, CM, CN, 
	CO, CR, CU, CV, CX, CY, CZ, DE, DJ, DK, DM, DO, DZ, EC, EE, 
	EG, ER, ES, ET, FI, FJ, FK, FM, FO, FR, GA, GB, GD, GE, GF, 
	GH, GI, GL, GM, GN, GP, GQ, GR, GT, GU, GW, GY, HK, HN, HR, 
	HT, HU, ID, IE, IL, IN, IQ, IR, IS, IT, JM, JO, JP, KE, KG, 
	KH, KI, KM, KN, KP, KR, KW, KY, KZ, LA, LB, LC, LK, LR, LS, 
	LT, LU, LV, LY, MA, MC, MD, ME, MG, MH, MK, ML, MM, MN, MO, 
	MP, MQ, MR, MS, MT, MU, MV, MW, MX, MY, MZ, NA, NC, NE, NF, 
	NG, NI, NL, NO, NP, NR, NU, NZ, OM, PA, PE, PF, PG, PH, PK, 
	PL, PM, PR, PS, PT, PW, PY, QA, RE, RO, RS, RU, RW, SA, SB, 
	SC, SD, SE, SG, SH, SI, SK, SL, SM, SN, SO, SR, ST, SV, SY, 
	SZ, TC, TD, TG, TH, TJ, TM, TN, TO, TR, TT, TV, TW, TZ, UA, 
	UG, UM, US, UY, UZ, VC, VE, VG, VI, VN, VU, WF, WS, YE, YT, 
	ZA, ZM, ZR, ZW ;
	
	public static Set<String> toStringSet () {
		final Set<String>  set = new HashSet<String>();
		for (CountryCode cc : values ()){
			set.add(cc.toString());
		}
		return set ;
	}
}
