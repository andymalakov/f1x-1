/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.f1x.api.message.fields;

// Generated by org.f1x.tools.DictionaryGenerator from QuickFIX dictionary
public enum CollAsgnRejectReason implements org.f1x.api.message.types.IntEnum {
	UNKNOWN_DEAL(0),
	UNKNOWN_OR_INVALID_INSTRUMENT(1),
	UNAUTHORIZED_TRANSACTION(2),
	INSUFFICIENT_COLLATERAL(3),
	INVALID_TYPE_OF_COLLATERAL(4),
	EXCESSIVE_SUBSTITUTION(5),
	OTHER(99);

	private final int code;

	CollAsgnRejectReason (int code) {
		this.code  = code;
	}

	public int getCode() { return code; }

	public static CollAsgnRejectReason parse(String s) {
		switch(s) {
			case "0" : return UNKNOWN_DEAL;
			case "1" : return UNKNOWN_OR_INVALID_INSTRUMENT;
			case "2" : return UNAUTHORIZED_TRANSACTION;
			case "3" : return INSUFFICIENT_COLLATERAL;
			case "4" : return INVALID_TYPE_OF_COLLATERAL;
			case "5" : return EXCESSIVE_SUBSTITUTION;
			case "99" : return OTHER;
		}
		return null;
	}

}