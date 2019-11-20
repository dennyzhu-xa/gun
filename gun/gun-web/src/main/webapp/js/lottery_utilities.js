﻿﻿﻿﻿		// used for validating chinese date...
		reDateFormat = /[0-9]{7}/;

		// this function sets the hidIsChecked hidden flag to "Y" if it is "N" and vice versa...
		function setCheckedFlag(hidField){
				 if(hidField.value == "N")
					hidField.value = "Y";
				else
					hidField.value="N";
		}
		
		// this function sets the hidIsChanged hidden flag to yes 
		function setChangedFlag(hidField){
				 hidField.value = "Y";
		}		
		
		// funtion to trim the white spaces from the string
		function trim(value)	{
			if(value!=null && value !='undefined'){
				var temp = value;
				 var obj = /^(\s*)([\W\w]*)(\b\s*$)/;
				 if (obj.test(temp)) { temp = temp.replace(obj, '$2'); }
				 var obj = / +/g;
				 temp = temp.replace(obj, " ");
				 if (temp == " ") { temp = ""; }
				 return temp;
			}
		}	
	
		
		function getSelectValue(objField) {
			if(objField == null) return;
			if(objField.options == null) return;
				 var optionLen = objField.options.length;
				 var fieldValue='';
				 for (var i=0;i<optionLen ;i++ ) {
				     if(objField.options[i].selected == true) {
						fieldValue=objField.options[i].value;
						break;
					 }//end of if	
				 } //end of for
				 return fieldValue;
	   }
	   
		function getSelectText(objField) {
				 var optionLen = objField.options.length;
				 var fieldText='';
				 for (var i=0;i<optionLen ;i++ ) {
				     if(objField.options[i].selected == true) {
						fieldText=objField.options[i].text;
						break;
					 }//end of if	
				 } //end of for
				 return fieldText;
	   }	   
	   
	   function getRadioValue(objField) {
				var radioLen =objField.length;
				if(radioLen==undefined){//表示可能只有一個radio
					if(objField!=null && objField.checked == true) {
						return objField.value;
					}else{
						return '';
					}
				}
				var fieldValue='';
				for (var i=0;i<radioLen ;i++ ) {
					if(objField[i].checked == true) {
						fieldValue=objField[i].value;
						break;
					}//end of if	
				} //end of for	   
				return fieldValue;
	   }
	   
	   function getRadioId(objField) {
		   var radioLen = objField.length;
		   if(radioLen==undefined){//表示可能只有一個radio
				if(objField!=null && objField.checked == true) {
					return objField.id;
				}else{
					return '';
				}
			}
		   var fieldId = '';
		   for (var i = 0; i < radioLen; i++) {
				if(objField[i].checked == true) {
					fieldId = objField[i].id;
					break;
				}
		   }
		   return fieldId;
	   }
	   
	   //date1,date2 format is YYMMDD
	   //if date1 before date2 return 1
	   //if date1 equals date2 reurn 0
	   //if date1 after date2 return -1
	   function compareDate(date1, date2) {
	   			var y1=date1.substring(0,2);
	   			var y2=date2.substring(0,2);
	   			if(y2 < y1)  return -1;
	   			else if(y2 > y1)  return 1;
	   			else {
			   		 var m1=date1.substring(2,4);
			   		 var m2=date2.substring(2,4);
			   		 if(m2 < m1) return -1;
			   		 else if(m2 > m1) return 1;
			   		 else {
			   				var d1=date1.substring(4,6);
	   						var d2=date2.substring(4,6);
			   				if(d2 < d1) return -1;
			   				else if(d2 > d1) return 1;
			   				else return 0;
			   		 }		
			   	}		 
	   }
	// type 1 format yyyy/mm/dd
	   //type 2 format yyy/mm/dd
	   //if date1 before date2 return 1
	   //if date1 equals date2 reurn 0
	   //if date1 after date2 return -1
	   
	   function compareDate(date1, date2, type) {
		   if(type == 1){
		    	var y1=date1.substr(0,4);
	   			var y2=date2.substr(0,4);
	   			if(y2 < y1)  return -1;
	   			else if(y2 > y1)  return 1;
	   			else {
			   		 var m1=date1.substr(5,2);
			   		 var m2=date2.substr(5,2);
			   		 if(m2 < m1) return -1;
			   		 else if(m2 > m1) return 1;
			   		 else {
			   				var d1=date1.substr(8,2);
	   						var d2=date2.substr(8,2);
			   				if(d2 < d1) return -1;
			   				else if(d2 > d1) return 1;
			   				else return 0;
			   		 }		
			   	}	
		    }
		   if(type == 2){
			   if(date1.length==8){
				   date1 = "0"+date1;
			   }
			   if(date2.length==8){
				   date2 = "0"+date2;
			   }
		    	var y1=date1.substr(0,3);
	   			var y2=date2.substr(0,3);
	   			if(y2 < y1)  return -1;
	   			else if(y2 > y1)  return 1;
	   			else {
			   		 var m1=date1.substr(4,2);
			   		 var m2=date2.substr(4,2);
			   		 if(m2 < m1) return -1;
			   		 else if(m2 > m1) return 1;
			   		 else {
			   				var d1=date1.substr(7,2);
	   						var d2=date2.substr(7,2);
			   				if(d2 < d1) return -1;
			   				else if(d2 > d1) return 1;
			   				else return 0;
			   		 }		
			   	}
		    }
	   }
	   
		//判斷兩個時間的大小
		//輸入的date1格式為：YYYY/MM/DD,或者YYYY/M/D  如：2010/01/05,2010/1/5
		//date1>date2 return 1;
		//date1<date2 return -1;
		//date1=date2 return 0;
		//輸入的date1或者date2有一個為空則Return false;
	   function compareCheckDate(date1, date2) {
			if(date1 == null || date1 == ""){
				return false;
			}
			if(date2 == null || date2 == ""){
				return false;
			}
			var date1temp = new Date(date1);
			var date2temp = new Date(date2);
			if(date1temp > date2temp) return 1;
			else if(date1temp < date2temp) return -1;
			else return 0;
	   }
	
		/**
		 * The function to replace string.
		 *originalStr:original string!AreplacedStr!Ga piece of the original string would be replaced, strReplace!Greplacing string.
		 **/			
		function replaceAll(originalStr,replacedStr,strReplace){
				 var index = 0;
				 while(originalStr.indexOf(replacedStr,index) != -1){
				 	   originalStr = originalStr.replace(replacedStr,strReplace);
					   index = originalStr.indexOf(replacedStr,index);
				 }
				 return originalStr;
		}

	   /**
		*  disable all input fields.
		**/
		function disableAllFields(objForm){
			     fobj=objForm.elements;
				 for (i=0;i<fobj.length;i++) {     
				     if (fobj[i].type=="text") {
				    	 objForm.elements[i].readOnly= true;
				     }
				     if (fobj[i].type=="select-one"){
				    	 objForm.elements[i].disabled=true;
				     }
				     if (fobj[i].type=="checkbox"){
				    	 objForm.elements[i].disabled=true;
				     }	
				     if (fobj[i].type=="textarea"){				    	 
				    	 objForm.elements[i].readOnly=true;
				     }
				     if (fobj[i].type=="radio"){
				    	 objForm.elements[i].disabled=true;
				     }
				 }		    
		}
		
		/**
		*  enable all input fields.
		**/
		function enableFields(objForm){
			     fobj=objForm.elements;
				 for (i=0;i<fobj.length;i++) {     
				     if (fobj[i].type=="text") {
				    	 objForm.elements[i].readOnly= false;
				     }
				     if (fobj[i].type=="select-one"){
				    	 objForm.elements[i].disabled=false;
				     }
				     if (fobj[i].type=="checkbox"){
				    	 objForm.elements[i].disabled=false;
				     }	
				     if (fobj[i].type=="textarea"){				    	 
				    	 objForm.elements[i].readOnly=false;
				     }
				     if (fobj[i].type=="radio"){
				    	 objForm.elements[i].disabled=false;
				     }
				 }		    
		}
		
		/**
		 * disable all buttons.
		 **/			
		function disableAllButtons(objForm){
				 fobj = objForm.elements;
				 for (i=0;i<fobj.length;i++) {
					  if (fobj[i].type=="button") {
					      if (objForm.elements[i].disabled ==false)
					          objForm.elements[i].disabled=true;
					  }
					  //input標籤，type="image"
					  if (fobj[i].type=="image") {
					        if (objForm.elements[i].disabled ==false)
					              objForm.elements[i].disabled=true;
					   }
				 }
				 //img標籤
				 var imgs = document.getElementsByTagName("img"); 
				 for(j=0;j<imgs.length;j++){
					 if (imgs[j].disabled ==false)
						 imgs[j].disabled=true;
				 }
				//image標籤
				 var images = document.getElementsByTagName("image"); 
				 for(k=0;j<images.length;j++){
					 if (images[k].disabled ==false)
						 images[k].disabled=true;
				 }
		}
		/**
		 * enable all buttons
		 **/			
		function enableAllButtons(objForm){
				 fobj = objForm.elements;
				 for (i=0;i<fobj.length;i++) {		     
					  if (fobj[i].type=="button") {
					        if (objForm.elements[i].disabled ==true)
					              objForm.elements[i].disabled=false;
					   }
					//input標籤，type="image"
					  if (fobj[i].type=="image") {
					        if (objForm.elements[i].disabled ==true)
					              objForm.elements[i].disabled=false;
					   }
				 }    
				//img標籤
				 var imgs = document.getElementsByTagName("img"); 
				 for(j=0;j<imgs.length;j++){
					 if (imgs[j].disabled ==true)
						 imgs[j].disabled=false;
				 }
				//image標籤
				 var images = document.getElementsByTagName("image"); 
				 for(k=0;k<images.length;k++){
					 if (images[k].disabled ==true)
						 images[k].disabled=false;
				 }
		}			

		

		function parseAmount(amount) {
				for (;amount.indexOf(',') != -1;) {
						amount = amount.replace(',','');
				}
				amount=parseFloat(amount,10);
				return amount;
		}

		// get someone's age
		function getAge(birthday) {
				var today=new Date();
				var curyy=parseInt(today.getYear(),10) - 1911;
				var curmm=parseInt(today.getMonth(),10)+1;
				var curdd=today.getDate();
				curmm+= curyy*12;
				var ageyy;
				var agemm;
				var agedd;
				if (birthday.length>6) {
					ageyy=parseInt(birthday.substr(0,3),10)*12;
					agemm=ageyy + parseInt(birthday.substr(3,2),10);
					agedd=parseInt(birthday.substr(5,2),10);
				} else {
					ageyy=parseInt(birthday.substr(0,2),10)*12;
					agemm=ageyy + parseInt(birthday.substr(2,2),10);
					agedd=parseInt(birthday.substr(4,2),10);
				}
				var s=curmm-agemm;
				if(s<6) return 0;
				if(s==6 && curdd<agedd) return 0;
				var r=parseInt(s/12,10);
				var m=s%12
				if(curdd<agedd) m-=1;
				if(m>5) r+=1;
				return r;
		}

		var character= " 0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-=[];',./~!@#$%^&*()_+{}|:<>?";
		var fullMode="　０１２３４５６７８９ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ－＝［］；、，。／～！＠＃＄％︿＆＊（）＿＋｛｝｜：＜＞？";
		
		function toFullMode(s) {
				if (isEmpty(s)) return false;
				var tbyte;
				var fullModeStr='';
				var tchar;
				
				for (var i=0 ; i < s.length ; i++) {
					tbyte=s.charCodeAt(i);
					tchar=s.charAt(i);
					if (tbyte >= 0 && tbyte <= 255) {
						var isValid=false;
						for(var j=0;j<character.length;j++) {
							if(character.charAt(j)==tchar) {
								fullModeStr+=fullMode.charAt(j);
								isValid=true;
								break;
							}
						}
						if(isValid==false) return -1;
					}else {
							fullModeStr+=tchar;
					}
				}
				return fullModeStr;
		}		

		
		//check whether a specific value of field is empty or not.
		function isEmpty(s) {
				 s=trim(s);
				 if ((s.length==0) || (s=="")) return true;
				 for (var i=0 ; i < s.length ; i++) {
					 if (s[i]!=" ") return false;
				 }
				 return true;
		}
		
		//fill length characters with the full-mode space character 
		function swfmode(s,length) {
						var i
						if (isEmpty(s)) return (false);
						var tbyte;
						for (var i=s.length ; i <length  ; i++)
						{
							s=s.concat("!@");
						}
						return s;
		}
		
		//The function to verify whether the personal id is legal or not.
		var LegalID = "0123456789";
		function islegalpid(id) {   
					   var fResult=true;
					   var value = 0;
					   var sId=id;
					   if(sId.length < 10) fResult=false;
					   else if(sId.length > 10 && sId.charAt(10) != '0') fResult=false;
					   else {
							     if (sId.length>10) sId = id.substring(0,10);
							     
						   		 if((sId.charAt(0)=='A') || (sId.charAt(0)=='a')) value=10
							     else if((sId.charAt(0)=='B') || (sId.charAt(0)=='b')) value=11
							     else if((sId.charAt(0)=='C') || (sId.charAt(0)=='c')) value=12
							     else if((sId.charAt(0)=='D') || (sId.charAt(0)=='d')) value=13
							     else if((sId.charAt(0)=='E') || (sId.charAt(0)=='e')) value=14
							     else if((sId.charAt(0)=='F') || (sId.charAt(0)=='f')) value=15
							     else if((sId.charAt(0)=='G') || (sId.charAt(0)=='g')) value=16
							     else if((sId.charAt(0)=='H') || (sId.charAt(0)=='h')) value=17
							     else if((sId.charAt(0)=='J') || (sId.charAt(0)=='j')) value=18
							     else if((sId.charAt(0)=='K') || (sId.charAt(0)=='k')) value=19
							     else if((sId.charAt(0)=='L') || (sId.charAt(0)=='l')) value=20
							     else if((sId.charAt(0)=='M') || (sId.charAt(0)=='m')) value=21
							     else if((sId.charAt(0)=='N') || (sId.charAt(0)=='n')) value=22
							     else if((sId.charAt(0)=='P') || (sId.charAt(0)=='p')) value=23
							     else if((sId.charAt(0)=='Q') || (sId.charAt(0)=='q')) value=24
							     else if((sId.charAt(0)=='R') || (sId.charAt(0)=='r')) value=25
							     else if((sId.charAt(0)=='S') || (sId.charAt(0)=='s')) value=26
							     else if((sId.charAt(0)=='T') || (sId.charAt(0)=='t')) value=27
							     else if((sId.charAt(0)=='U') || (sId.charAt(0)=='u')) value=28
							     else if((sId.charAt(0)=='V') || (sId.charAt(0)=='v')) value=29
							     else if((sId.charAt(0)=='X') || (sId.charAt(0)=='x')) value=30
							     else if((sId.charAt(0)=='Y') || (sId.charAt(0)=='y')) value=31
							     else if((sId.charAt(0)=='W') || (sId.charAt(0)=='w')) value=32
							     else if((sId.charAt(0)=='Z') || (sId.charAt(0)=='z')) value=33
							     else if((sId.charAt(0)=='I') || (sId.charAt(0)=='i')) value=34
							     else if((sId.charAt(0)=='O') || (sId.charAt(0)=='o')) value=35
							     else fResult = false ;
					   }
					   if(fResult==true) {
					     	value = Math.floor(value/10) + (value%10)*9 +
							             parseInt(sId.charAt(1),10)*8+
							             parseInt(sId.charAt(2),10)*7+
							             parseInt(sId.charAt(3),10)*6+
							             parseInt(sId.charAt(4),10)*5+
							             parseInt(sId.charAt(5),10)*4+
							             parseInt(sId.charAt(6),10)*3+
							             parseInt(sId.charAt(7),10)*2+
							             parseInt(sId.charAt(8),10)+
							             parseInt(sId.charAt(9),10) ;
					     	value = value % 10 ;
					    	 if(value!=0) fResult = false ;
					
						     var i;
						     var c;
						     for (i = 1; i < sId.length; i++) {
								      c = sId.charAt(i);
								      if (LegalID.indexOf(c) == -1) fResult = false;
						     }
					
							var a=1;
							var b;
							b=sId.charAt(a);
							if (b==1); 
							else if (b==2); 
							else{ fResult = false ;}
					    }
					    if(sId.length==8) {
					      fResult=(!isNaN(sId));
					    }  
					    return fResult ;
		}

		//The function to verify whether the merchant(company) id is legal or not
		function islegalcid(id) {
					var comNo=id;
					var res = new Array(8);
					var key = "12121241";
					var isModeTwo = false;//第七個數是否為七
					var result = 0;
					
					if(comNo.length != 8) return false ;


					for(var i=0; i<8; i++) {
						var tmp = comNo.charAt(i) * key.charAt(i);
						res[i] = Math.floor(tmp/10) + (tmp%10);//取出十位數和個位數相加
						if(i == 6 && comNo.charAt(i) == 7)
							isModeTwo = true;
				    }
					for(var s=0; s<8; s++)
						result += res[s];

					if(isModeTwo){
						if((result % 10) != 0 && ((result + 1) % 10) != 0){//如果第七位數為7
							return false ;
				    	}
					} else
				    	if((result % 10) != 0){
				    		return false ;
				    	}    
				    return true;
		}
		
		//Check whether the data type of column is numeric (include decimal point)
		var LegalNumeric = "0123456789.,-%";
		function isnumeric(s)	{
					    var i;
					    var j=0;
					    var c;
					    for (i = 0; i < s.length; i++)  {
						       c = s.charAt(i);
						       if (LegalNumeric.indexOf(c) == -1) return false;
						       
						       if (c == '.') j++;
						       if (j>1) return false;
						      
						       if (c=='-' && i!=0) return  false;	//If the value is a negative number
					    }
					    return true;
		}
		
		//Check whether the date is valid or not. add susanchen 2009/07/23
		 function isCheckDate(ymd){
		//	var regu = /^[0-9]{4}([\/,/])(0[1-9]|[1-9]|1[0-2])([\/,/])((0[1-9]|[1-9])|1[0-9]|2[0-9]|3[0-1])$/;
			var regu = /^((^((1[8-9]\d{2})|([2-9]\d{3}))([\/])(10|12|0?[13578])([\/])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([\/])(11|0?[469])([\/])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([\/])(0?2)([\/])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([\/])(0?2)([\/])(29)$)|(^([3579][26]00)([\/])(0?2)([\/])(29)$)|(^([1][89][0][48])([\/])(0?2)([\/])(29)$)|(^([2-9][0-9][0][48])([\/])(0?2)([\/])(29)$)|(^([1][89][2468][048])([\/])(0?2)([\/])(29)$)|(^([2-9][0-9][2468][048])([\/])(0?2)([\/])(29)$)|(^([1][89][13579][26])([\/])(0?2)([\/])(29)$)|(^([2-9][0-9][13579][26])([\/])(0?2)([\/])(29)$))$/;
			if (regu.test(ymd)) 
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		 
		 function isCheckChineseDate(ymd){
				//	var regu = /^[0-9]{4}([\/,/])(0[1-9]|[1-9]|1[0-2])([\/,/])((0[1-9]|[1-9])|1[0-9]|2[0-9]|3[0-1])$/;
					var regu = /^((^(([0-9]\d{1})|([0-9]\d{2}))([\/])(10|11|12|0?[1-9])([\/])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([\/])(11|0?[469])([\/])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([\/])(0?2)([\/])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([\/])(0?2)([\/])(29)$)|(^([3579][26]00)([\/])(0?2)([\/])(29)$)|(^([1][89][0][48])([\/])(0?2)([\/])(29)$)|(^([2-9][0-9][0][48])([\/])(0?2)([\/])(29)$)|(^([1][89][2468][048])([\/])(0?2)([\/])(29)$)|(^([2-9][0-9][2468][048])([\/])(0?2)([\/])(29)$)|(^([1][89][13579][26])([\/])(0?2)([\/])(29)$)|(^([2-9][0-9][13579][26])([\/])(0?2)([\/])(29)$))$/;
					if (regu.test(ymd)) 
					{
						return true;
					}
					else
					{
						return false;
					}
				}
		
		//Check whether the date is valid or not. add boltenDeng 2009/07 或 2009-07
		 function isCheckYearAndMonth(ymd){
			 if (ymd.length>7 || ymd.length<6) return false; 
			 if (ymd.length>6) {
				   y=parseInt(ymd.substr(0,4),10);
				   m=parseInt(ymd.substr(5,7),10);
			 }else{
				   y=parseInt(ymd.substr(0,4),10);
				   m=parseInt(ymd.substr(5,6),10);
			}
			if ((m<=0)||(m >12)) { 
				return false ;
			}
			return true;
			 
		}
		
		//Check whether the chinese date is valid or not.
		function isdate(ymd) {
						if (!isInteger(ymd)) return false; 
						if (ymd.length>7 || ymd.length<6) return false; 
						if (ymd.length>6) {
						   y=parseInt(ymd.substr(0,3),10);
						   m=parseInt(ymd.substr(3,2),10);
						   d=parseInt(ymd.substr(5,2),10);
						} else	{
						   y=parseInt(ymd.substr(0,2),10);
						   m=parseInt(ymd.substr(2,2),10);
						   d=parseInt(ymd.substr(4,2),10);
						}
						if ((m<=0)||(m >12)||(d<=0)||(d>31)) { 
							return false ;
						}
						if ((m==1)||(m==3)||(m==5)||(m==7)||(m==8)||(m==10)||(m==12)) {                                                                                                               
							 	if ((d<1) || (d>31)) { return false; }                                                                                                            
						}else if ((m==4)||(m==6)||(m==9)||(m==11)) {                                                                                                               
						        if ((d<1) || (d>30)) { return false; }                                                                                                            
						}else if (m==2) {
						  	   var leap;
						  	   var yy = (parseInt(y,10) + 1911);
						  	   if (yy%4==0)
						  	      if (yy%100==0) 
						  	         if (yy%400==0) leap=true;
						  	         else leap=false;
						  	      else leap=true;
						  	   else leap=false;
						  	   if(!leap && (d>28)) { return false; }
						           if(leap && (d>29)) { return false; }
						}else { 
								return true;
						}
						return true;
		}

		//Check whether the date type of field is integer
		function isInteger(s) {
						var i;
						var j=0;
						var c;
						for (i = 0; i < s.length; i++)  {
								c = s.charCodeAt(i);
								if (c<48 || c>57) return false;
						}
						return true;
		}		

		//check whether all characters are full mode or not.
		function isfmode(s) {
						if (isEmpty(s)) return false;
						var tbyte;
						for (var i=0 ; i < s.length ; i++) {
							tbyte=s.charCodeAt(i);
							if (tbyte >= 0 && tbyte <= 255) return false;
						}
						return true;
		}
		
		//check whether the format of email is valid or not.
		var LegalEmailChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_.@-"
		function isemail(email){   
					    var i;
					    var c;
					    var j=0;
					    var dot=0;
					    if (isEmpty(email)) return false;
					    
					    for (i = 0; i < email.length; i++) {
						     c = email.charAt(i);
						     if (LegalEmailChar.indexOf(c) <0)  return false;
						     if (c == "@") j++;
						     if (c == '.') dot++;
						     if (j>1) return false;
					    }
					    if(j==0 || dot==0) return false;
					    
					    return true;
		}

		//check whether the securities account no is valid or not.
		function isSecAccNo(seccd, secAccNo) {
					
					  if(seccd.length<4|| secAccNo.length<7) return false;
					  
					  var comp=seccd.substring(0,3);
					  var area=seccd.charAt(3);
					  var checkno=0;
					  var tempcd=0;	
					  var checksum=0;
						if(isdigital(area)){
							tempcd=parseInt(area,10);
						}
						else{
								var asciicode=area.charCodeAt(0);
								 if(asciicode>=65 &&  asciicode<=90) {
									tempcd=(asciicode-65)%10;
								}else {
										tempcd=(asciicode-96)%10;
								}
						}
						
						checksum=parseInt(comp.charAt(0),10)*1+parseInt(comp.charAt(1),10)*3+parseInt(comp.charAt(2),10)*7;
						checksum+=tempcd;
						checksum+=parseInt(secAccNo.charAt(0),10)*1+parseInt(secAccNo.charAt(1),10)*3+parseInt(secAccNo.charAt(2),10)*7+parseInt(secAccNo.charAt(3),10)*1+parseInt(secAccNo.charAt(4),10)*3+parseInt(secAccNo.charAt(5),10)*7;
						checkno=10-(checksum%10);
						if(checkno==10) checkno=0;
						if(parseInt(secAccNo.charAt(6),10)!=checkno) {
						   return false;
						} else return true;   
		}

		//check whether the value of the amount of money is valid or not.
		function isValidAmount(objField) {
			 
						var amount = objField.value;
						for (;amount.indexOf(',') != -1;) amount = amount.replace(',','');
		
						if(!(parseFloat(amount,10)==(amount*1))) return false;
						else return true;
		}
		
		// function checks for the duplicate values...
		function isduplicate( txtField ) {
						var strValue1 =txtField.value;
						if(strValue1=="") return false;
					
						var strObjName =txtField.name;
						var intIndex=0;
					
						for(intIndex=0;intIndex<strObjName.length;intIndex++) {
								if(!isNaN(strObjName.charAt(intIndex))) {
									break;
								}
						} // end of for loop...
						strObjName = strObjName.substr(0,intIndex);
					
						var obj = null;
					
						intIndex = 1;			
						obj = document.getElementById(strObjName+intIndex);
					
						while(obj) {
								if( obj != txtField ) {
									if(obj.value==strValue1) {
										if(!txtField.disabled) // before setting the focus check if the obj is not disabled...
											txtField.value="";
										txtField.focus();
										return true;
									}
								}
								intIndex++;
								obj = document.getElementById(strObjName+intIndex);
						} // end of for loop...
						return false;
		} // end of the function...


		
		function isdigital(value) {   
						var s= trim(value);
						for (i = 0; i < s.length; i++) {   
							var c = s.charAt(i);
							if (!isdigit(c)) return false;
						}
						return true;
		}
		
		
		function isdigit (c) {
		   				return ((c >= "0") && (c <= "9"));
		}
		
		function containSpaces(value){
						for(var i=0;i<value.length;i++)	{
							if(value.charAt(i)==' ')	return true;
						}
						return false;
		}

		// checks form field and shows alert in case of error
		// validFunctionName - pointer to a string that gives one
		// string parameter and returns true in case of valid
		// value else false
		function checkField(objField, validFunctionName, msg){
		    if(objField==null) return true;
		    if(objField.disabled) return true;
		    
		    eval("var isValid = " + validFunctionName + "(objField)");
		
		    if (isValid)
		        return true;
		
		    alert(msg);
		    objField.focus();
		    return false;
		}
		
		function isEmptyField(objField, msg) {
			if(objField==null) return false;
			if(objField.disabled)return false;
			var fieldValue;
			if (objField.type=="text") {
				fieldValue=objField.value;
			}else if(objField.type=="select-one"){
				fieldValue=getSelectValue(objField);
			}else if(objField.type==undefined){
				fieldValue=getRadioValue(objField);
			}else {
				fieldValue=objField.value;
			}			
			       
			if(isEmpty(fieldValue)) {
				alert(msg);
				objField.focus();
				return true;
			}
				     
			return false;
		}
		
		function popup(objForm, windowName, option) {
				var defaultOption='status=false,scrollbars=false';
				if(!isEmpty(option)) defaultOption=option;
				var newwn = window.open('', windowName,defaultOption);
				if(newwn!=null) {
					objForm.target = windowName;
					objForm.submit();
					objForm.target=self.name;
					opener=null;
					window.close();
					return true;
				}else {
					return false;
				}		
		}	
		/**
		 * 提交表單的公用方法。
		 * @param objForm
		 * @param useCaseNo
		 * @param serviceId
		 * @param actionId
		 */
	    function actionClicked(objForm, useCaseNo, serviceId, actionId) {
	    		 disableAllButtons(objForm); 
	    		 objForm.useCaseNo.value = useCaseNo;
		    	 objForm.serviceId.value = serviceId;
				 objForm.actionId.value = actionId;
				 objForm.submit();
		}
	    
	    function actionClickedByFormAction(objForm, useCaseNo, serviceId, actionId, formAction) {
   		 	 disableAllButtons(objForm); 
	    	 objForm.useCaseNo.value = useCaseNo;
	    	 objForm.serviceId.value = serviceId;
			 objForm.actionId.value = actionId;
			 objForm.action = formAction;
			 objForm.submit();
	    }		
				
		function checkAllItems(checkObj, checkedSize, chkSelectItemName, isSelectedName) {
				 for(var i=0;i<checkedSize;i++) {
						obj=document.getElementById(chkSelectItemName+i);
						if(obj!=null&&!obj.disabled){
							obj.checked=checkObj.checked;
							if(checkObj.checked==true){
							 	document.getElementById(isSelectedName+i).value='Y';
							}else {
							 	document.getElementById(isSelectedName+i).value='N';
							 }
						}	
				}
		}	
		//merge from beans
		//檢查外國人暫時身份證字號 id
		function islegalfid(id) {
			var flag = true;
			//格式為FA12345679 共10碼
			if (id.length != 10) {
				flag = false;
				return flag;
			}
			var idValue = id.value;
			var firstId = idValue.charAt(0).toUpperCase();
			var secondId = idValue.charAt(1).toUpperCase();
			//除了第1,2碼及最後檢查碼之外的值
			var lastId = idValue.substring(2,id.length-2);
			//第一碼英文轉成數字的值
			var calculateFirst;
			//第二碼英文轉成數字的值
			var calculateSecond;	
			//用公式算出的總數然後和檢查碼比對
			var totalValue = 0;
			var tempValue;
			
			switch (firstId) {
			case A : calculateFirst = 10;
				break;
			case B : calculateFirst = 11;
				break;
			case C : calculateFirst = 12;
				break;
			case D : calculateFirst = 13;
				break;
			case E : calculateFirst = 14;
				break;
			case F : calculateFirst = 15;
				break;		
			case G : calculateFirst = 16;
				break;
			case H : calculateFirst = 17;
				break;
			case J : calculateFirst = 18;
				break;
			case K : calculateFirst = 19;
				break;
			case L : calculateFirst = 20;
				break;
			case M : calculateFirst = 21;
				break;
			case N : calculateFirst = 22;
				break;		
			case P : calculateFirst = 23;
				break;		
			case Q : calculateFirst = 24;
				break;
			case R : calculateFirst = 25;
				break;
			case S : calculateFirst = 26;
				break;
			case T : calculateFirst = 27;
				break;
			case U : calculateFirst = 28;
				break;
			case V : calculateFirst = 29;
				break;		
			case X : calculateFirst = 30;
				break;
			case Y : calculateFirst = 31;
				break;			
			case W : calculateFirst = 32;
				break;		
			case Z : calculateFirst = 33;
				break;		
			case I : calculateFirst = 34;
				break;		
			case O : calculateFirst = 35;
				break;
			default: calculateFirst = 99;
				break;
			}

			switch (secondId) {
			case A : calculateSecond = 0;
				break;
			case B : calculateSecond = 1;
				break;
			case C : calculateSecond = 2;
				break;
			case D : calculateSecond = 3;
				break;
			case E : calculateSecond = 4;
				break;
			case F : calculateSecond = 5;
				break;
			default: calculateSecond = 99;
				break;		
			}
			
			//第一或第二碼格式錯誤
			if (calculateFirst == 99 || calculateSecond == 99) {
				flag = false;
				return flag;
			}
			//開始計算totalValue
			totalValue = eval(totalValue + calculateFirst.charAt(0));
			tempValue = calculateFirst.charAt(1)*9;
			totalValue = eval(totalValue + tempValue.charAt(tempValue.length-1));
			tempValue = calculateSecond*8;
			totalValue = eval(totalValue + tempValue.charAt(tempValue.length-1));
			
			for (var i = 0; i < lastId.length; i++) {
				tempValue = lastId.charAt(i)*eval(7-i);
				totalValue = eval(totalValue + tempValue.charAt(tempValue.length-1));
			}

			//最後的公式計算 10-totalValue
			tempValue = eval(10 - totalValue.charAt(totalValue.length - 1));
			alert(tempValue);
			//比對檢查碼
			if (tempValue.charAt(tempValue.length - 1) != idValue.charAt(idValue.length - 1) ) {
				flag = false;
			}
				
			return flag;
		}
		
		// 檢查外國人暫時身份證字號 id - add by Iven 2009/12/06
		function isForeignerPid(id) {
			var flag = true;
			//格式為FA12345679 共10碼
			if (id.value.length != 10) {
				flag = false;
				return flag;
			}
			
			var idValue = id.value;
			var firstId = idValue.charAt(0).toUpperCase();
			var secondId = idValue.charAt(1).toUpperCase();
			//除了第1,2碼及最後檢查碼之外的值
			var lastId = idValue.substring(2, idValue.length-1);
			//第一碼英文轉成數字的值
			var calculateFirst;
			//第二碼英文轉成數字的值
			var calculateSecond;
			//用公式算出的總數然後和檢查碼比對
			var totalValue = 0;
			var tempValue;
			
			switch (firstId) {
			case 'A' : calculateFirst = '10';
				break;
			case 'B' : calculateFirst = '11';
				break;
			case 'C' : calculateFirst = '12';
				break;
			case 'D' : calculateFirst = '13';
				break;
			case 'E' : calculateFirst = '14';
				break;
			case 'F' : calculateFirst = '15';
				break;		
			case 'G' : calculateFirst = '16';
				break;
			case 'H' : calculateFirst = '17';
				break;
			case 'J' : calculateFirst = '18';
				break;
			case 'K' : calculateFirst = '19';
				break;
			case 'L' : calculateFirst = '20';
				break;
			case 'M' : calculateFirst = '21';
				break;
			case 'N' : calculateFirst = '22';
				break;		
			case 'P' : calculateFirst = '23';
				break;		
			case 'Q' : calculateFirst = '24';
				break;
			case 'R' : calculateFirst = '25';
				break;
			case 'S' : calculateFirst = '26';
				break;
			case 'T' : calculateFirst = '27';
				break;
			case 'U' : calculateFirst = '28';
				break;
			case 'V' : calculateFirst = '29';
				break;		
			case 'X' : calculateFirst = '30';
				break;
			case 'Y' : calculateFirst = '31';
				break;			
			case 'W' : calculateFirst = '32';
				break;		
			case 'Z' : calculateFirst = '33';
				break;		
			case 'I' : calculateFirst = '34';
				break;		
			case 'O' : calculateFirst = '35';
				break;
			default: calculateFirst = '99';
				break;
			}
			
			switch (secondId) {
			case 'A' : calculateSecond = '0';
				break;
			case 'B' : calculateSecond = '1';
				break;
			case 'C' : calculateSecond = '2';
				break;
			case 'D' : calculateSecond = '3';
				break;
			case 'E' : calculateSecond = '4';
				break;
			case 'F' : calculateSecond = '5';
				break;
			default: calculateSecond = '99';
				break;		
			}
			
			//第一或第二碼格式錯誤
			if (calculateFirst == '99' || calculateSecond == '99') {
				flag = false;
				return flag;
			}
			
			//開始計算totalValue
			totalValue = parseInt(calculateFirst.charAt(0)) * 1;
			tempValue = '' + parseInt(calculateFirst.charAt(1)) * 9;
			totalValue = parseInt(totalValue) + parseInt(tempValue.charAt(tempValue.length-1));
			tempValue = '' + parseInt(calculateSecond) * 8;
			totalValue = parseInt(totalValue) + parseInt(tempValue.charAt(tempValue.length-1));
			for (var i = 0; i < lastId.length; i++) {
				tempValue = '' + (lastId.charAt(i) * (7-i));
				totalValue = parseInt(totalValue) + parseInt(tempValue.charAt(tempValue.length-1));
			}

			//最後的公式計算 10-totalValue
			totalValue = '' + totalValue;
			tempValue = '' + (10 - parseInt(totalValue.charAt(totalValue.length - 1)));
			//比對檢查碼
			if (tempValue.charAt(tempValue.length - 1) != idValue.charAt(idValue.length - 1) ) {
				flag = false;
			}
			
			return flag;
		}
		
		//檢查外國人暫時身份證字號 id. add by Iven 2009/10/02
		// fid格式為：12345679FA.
		// 共10碼，且前八碼為西元年月日yyyyMMdd的格式，後兩碼為英文
		function islegalfid2(fid) {
			var idValue = fid.value;
			//共10碼（格式為：12345679FA）
			if (idValue.length != 10) {
				return false;
			}
			//前8碼（西元年月日yyyyMMdd）
			var yyyyMMdd = idValue.substr(0,8);
			if (!isDate4YYYYMMDD(yyyyMMdd)) {
				return false;
			}
			//後兩碼為英文
			if (!isletterstring(idValue.substr(8))) {
				return false;
			}
			return true;
		}
		
		// Check whether the date is valid or not. add by Iven 2009/10/02
		// date format: 西元年月日yyyyMMdd
		function isDate4YYYYMMDD(yyyyMMdd){
			if (yyyyMMdd.length != 8) return false;
			
			var yyyy = parseInt(yyyyMMdd.substr(0,4),10);
		    var MM = parseInt(yyyyMMdd.substr(4,2),10);
		    var dd = parseInt(yyyyMMdd.substr(6,2),10);
		    
			if ((MM<=0)||(MM >12)||(dd<=0)||(dd>31)) { 
				return false;
			}
			if ((MM==1)||(MM==3)||(MM==5)||(MM==7)||(MM==8)||(MM==10)||(MM==12)) {                                                                                                               
				if ((dd<1) || (dd>31)) { return false; }
			}else if ((MM==4)||(MM==6)||(MM==9)||(MM==11)) {
			    if ((dd<1) || (dd>30)) { return false; }
			}else if (MM==2) {
		  		var leap;
		  		if (yyyy%4==0)
		  	      if (yyyy%100==0) 
		  	         if (yyyy%400==0) leap=true;
		  	         else leap=false;
		  	      else leap=true;
		  		else leap=false;
		  		if(!leap && (dd>28)) { return false; }
		        if(leap && (dd>29)) { return false; }
			}else {
				return true;
			}
			return true;
		}
		
		//判斷傳入字符串是否為英文字母. add by Iven 2009/10/02
		function isletterstring(str) {
			var reg=/^[a-zA-Z]+$/;     
			return reg.test(str);
		}
		 
		//格式化數字為#,###
		function formatNumber(str) {
		    str += '';   
		    x = str.split('.');   
		    x1 = x[0];   
		    x2 = x.length > 1 ? '.' + x[1] : '';   
		    var rgx = /(\d+)(\d{3})/;   
		    while (rgx.test(x1)) {   
		    x1 = x1.replace(rgx, '$1' + ',' + '$2');   
		    }   
		    return x1 + x2;
		}
		//disabled或readonly欄位
		function disableField(elm, isDisable) {
			if(isDisable == 'Y') {
				for(i=0;i<elm.length;i++) {
					if(elm[i].type == 'text')
						elm[i].readOnly = true;
					else if (elm[i].type == 'textarea')				    	 
				    	 elm[i].readOnly=true;				    
					else
						elm[i].disabled = true;
				}
			} else if (isDisable == 'N') {
				for(i=0;i<elm.length;i++)
					if(elm[i].type == 'text')
						elm[i].readOnly = false;
					else if (elm[i].type == 'textarea')				    	 
				    	 elm[i].readOnly=false;
					else
						elm[i].disabled = false;
			}
		}
		
		//驗證number整數部分的長度 
		function checkNumber(bitCount,value){
			if(value==null || value==''){
				return false;
			}
			if(!isnumeric(value)){
				return false;
			}
			var segments = value.split('.');
			if(segments!=null&&segments.length>=1){
				segmentFirst = segments[0];
				//去掉"-"
				segmentFirst = segmentFirst.replace(new RegExp("-","gm"),"");
				//去掉","
				segmentFirst = segmentFirst.replace(new RegExp(",","gm"),"");
				//去掉"%"
				segmentFirst = segmentFirst.replace(new RegExp("%","gm"),"");
				if(segmentFirst!=null&&segmentFirst!=""&&isnumeric(segmentFirst)&&segmentFirst.length<=bitCount){
					return true;
				}
				return false;
			}
			return false;
		}
		//驗證數據類型Number(p,s)，長度是否超過p
		function checkNumberLength(amount, len){
			var segments = amount.split('.');
			var stringLen = "";
			if(segments!=null && segments.length >= 1){
				var segmentFirst = segments[0];
				if(segmentFirst != null && segmentFirst != ""){
					//去掉"-"
					segmentFirst = segmentFirst.replace(new RegExp("-","gm"),"");
					//去掉","
					segmentFirst = segmentFirst.replace(new RegExp(",","gm"),"");
					stringLen = segmentFirst.length;
				}
				var segmentSecend = segments[1];
				if(segmentSecend != null && segmentSecend != ""){
					stringLen = segmentFirst.length + segmentSecend.length;
				}
				if(stringLen > len){
					return false;
				}
				return true;
			}
		}
		//驗證金額是否為正確格式:可以是兩位小數,全為正整數,只能輸入數字.
		function checkIsRightAmount(amount){
			var regu = /^(([1-9]\d*)|0)(\.\d{1,2})?$/;
			amount = amount.replace(new RegExp(",","gm"),"");
			if (regu.test(amount)){
				return true;
			}else{
				return false;
			}
		}
				
		/*
		用途：檢查輸入物件的值是否符合正整數格式(包含0)
		輸入：objField    物件欄位
		輸入：lengthLimit 長度限制(去除千分位後)
		返回：如果通過驗證返回true,否則返回false
		added by Jun 2009/09/16		
		*/
		 function isPositiveAmount(objField, lengthLimit){
			 var value;
			 for(i=0;i<objField.length;i++) {
				 value = objField[i].value;
				 for (;value.indexOf(',') != -1;) value = value.replace(',','');
				 if (!isInteger(value)) {
					 objField[i].focus();
					 return false;
				 }	 
				 if (value.length > lengthLimit){
					 objField[i].focus();
					 return false;
 			 	 }
			 }	 
			 return true;
		 }
		/*
		用途：droplist等沒有readonly的屬性, 利用span控制, 達到同樣效果.
		輸入：obj   物件欄位
		注意: 如果要使用此javascript, 頁面必須在droplist等控件外層包上<span>標籤對.
		added by susan chen 2009/11/10
		*/
		function setReadOnly(obj){
			obj.style.backgroundColor = "#EEEEEE";
			var parentObj = obj.parentNode;
		    if(parentObj!=null){
		        parentObj.onbeforeactivate = function(){this.setCapture();return false;};
		    	parentObj.onmousemove = function(){this.setCapture();};
				parentObj.onmouseout = function(){this.releaseCapture();};
				parentObj.onfocus = function(){this.blur();};
		    }
		}
		
		/*
		 用途：droplist等沒有readonly的屬性, 利用span控制, 達到同樣效果.
		 輸入：id 標簽的id屬性
		 注意: 如果要使用此javascript, 頁面必須在droplist等控件外層包上<span>標籤對.
		 added by JudeJiao 2011/07/08 IPM-2807
		 */
		/*function setReadOnlyById(id){
			var obj = document.getElementById(id);
			if(obj == null) return;
			var parentObj = obj.parentNode;
		    if(parentObj!=null){
		        parentObj.onbeforeactivate = function(){this.setCapture();return false;};
		    	parentObj.onmousemove = function(){this.setCapture();};
				parentObj.onmouseout = function(){this.releaseCapture();};
				parentObj.onfocus = function(){this.blur();};
		    }
		}*/
		
		function setReadOnlyById(id){
			var parentObj = document.getElementById(id);
		    if(parentObj!=null){
		        parentObj.onbeforeactivate = function(){this.setCapture();return false;};
		    	parentObj.onmousemove = function(){this.setCapture();};
				parentObj.onmouseout = function(){this.releaseCapture();};
				parentObj.onfocus = function(){this.blur();};
		    }
		}
		
		
		/*
		 用途：droplist等沒有readonly的屬性, 利用span控制, 達到同樣效果.
		 輸入：name 標簽的name屬性
		 注意: 如果要使用此javascript, 頁面必須在droplist等控件外層包上<span>標籤對.
		 added by JudeJiao 2011/07/08 IPM-2807
		 */
		function setReadOnlyByTagName(name){
			var objs = document.getElementsByName(name);
			for(var i=0;i<objs.length;i++){
				var parentObj = objs[i].parentNode;
			    if(parentObj!=null){
			        parentObj.onbeforeactivate = function(){this.setCapture();return false;};
			    	parentObj.onmousemove = function(){this.setCapture();};
					parentObj.onmouseout = function(){this.releaseCapture();};
					parentObj.onfocus = function(){this.blur();};
			    }
			}
		}		
		
		/*
		用途：droplist等沒有readonly的屬性, 利用span控制, 達到同樣效果.
		輸入：obj   物件欄位
		注意: 如果要使用此javascript, 頁面必須在droplist等控件外層包上<span>標籤對.
		added by susan chen 2009/11/10
		*/
		function removeReadOnly(obj){
			obj.style.backgroundColor = "#FFFFFF";
			var parentObj = obj.parentNode;
			if(parentObj!=null){
				parentObj.onbeforeactivate = null;
				parentObj.onmousemove = null;
				parentObj.onmouseout = null;
				parentObj.onfocus = null;
			}
		} 
		//設置收件夾的鏈接 readOnly。 add By Bolten
		function setReadOnlyByName(objName){
			var obj = document.getElementById(objName);
		    if(obj!=null){
		    	obj.onbeforeactivate = function(){this.setCapture();return false;};
		    	obj.onmousemove = function(){this.setCapture();};
		    	obj.onmouseout = function(){this.releaseCapture();};
		    	obj.onfocus = function(){this.blur();};
		    }
		}
		
		/*
		 * eLoan會有難字的部分(台新造字)，须於UI端新增附件程式碼.
		 */
		 function chkbig5(source){
		 	// by MS950字集（微軟版的BIG5）為準
		 	var table = new Array(256);
		 	table[0] = [65535,65535,65535,65535,65535,65535,65535,65535,1,0,59374,1983,65535,65535,65535,65535,];
		 	table[1] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[2] = [0,0,0,0,0,0,0,0,0,0,0,0,11904,512,0,0,];
		 	table[3] = [32,0,0,0,0,0,0,0,0,65534,1019,65534,1019,0,0,0,];
		 	table[4] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[5] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[6] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[7] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[8] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[9] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[10] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[11] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[12] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[13] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[14] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[15] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[16] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[17] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[18] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[19] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[20] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[21] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[22] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[23] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[24] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[25] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[26] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[27] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[28] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[29] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[30] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[31] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[32] = [0,13176,244,18476,0,0,0,0,0,0,4096,0,0,0,0,0,];
		 	table[33] = [552,0,0,0,0,0,1023,0,0,975,0,0,0,0,0,0,];
		 	table[34] = [0,50528,20009,48,0,4,203,0,0,544,32,32768,0,0,0,0,];
		 	table[35] = [128,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[36] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[37] = [4101,4369,4112,4112,0,65535,65535,31,65534,56,3,12300,51392,0,60,0,];
		 	table[38] = [608,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[39] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[40] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[41] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[42] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[43] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[44] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[45] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[46] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[47] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[48] = [65295,28727,1022,1,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[49] = [65504,65535,1023,0,0,0,0,0,0,65532,0,0,0,0,0,0,];
		 	table[50] = [0,0,65535,65535,15,0,0,0,65535,65535,65535,1,0,0,0,0,];
		 	table[51] = [0,0,0,0,0,0,0,0,49152,28672,2,0,16400,38,0,0,];
		 	table[52] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[53] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[54] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[55] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[56] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[57] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[58] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[59] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[60] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[61] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[62] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[63] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[64] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[65] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[66] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[67] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[68] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[69] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[70] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[71] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[72] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[73] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[74] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[75] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[76] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[77] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[78] = [65419,50035,26688,6927,59820,62284,512,49160,31068,51774,31094,1608,12255,63472,826,43263,];
		 	table[79] = [61239,9023,45060,64857,62410,65535,56991,65529,44031,32247,49152,36588,61119,65499,53251,17914,];
		 	table[80] = [64225,57342,49135,4267,65515,64682,61247,9469,30893,32630,61452,60927,53238,11514,63481,60267,];
		 	table[81] = [8189,38335,26231,49087,15355,65204,31662,4578,42625,16830,5173,29379,32112,29073,3,10091,];
		 	table[82] = [22475,28879,18226,3567,32474,64628,65030,48564,16287,35786,32329,22528,8847,60396,35420,56763,];
		 	table[83] = [61280,46823,41999,62099,14267,21662,53323,39855,50196,63444,12464,2580,12040,35024,65406,6447,];
		 	table[84] = [65498,64263,32753,31723,50671,16,39423,65023,31191,1383,65511,64971,50175,16448,28663,48526,];
		 	table[85] = [57338,1175,62656,23551,60795,53479,1150,63712,65439,46910,32254,34862,65533,48767,33790,63172,];
		 	table[86] = [62295,47357,54912,61309,22375,18312,65405,50143,61695,14249,32224,28924,16239,60570,19635,34433,];
		 	table[87] = [16286,56668,63245,18457,65187,7,44886,14591,38925,61368,16445,46944,55502,36917,29375,16383,];
		 	table[88] = [32759,31249,63419,44031,65280,28606,43324,65138,53231,61723,56171,64522,50150,61310,39836,62992,];
		 	table[89] = [61512,5876,65205,20866,51121,5563,28295,64479,58431,25549,49663,32382,65003,32095,30587,64766,];
		 	table[90] = [38411,56298,25129,21480,14303,65007,14069,48513,56344,64701,53988,65535,16343,65504,32623,45048,];
		 	table[91] = [39854,28377,62971,61717,31145,48635,23100,44463,56250,8108,29180,33657,31991,50015,57343,1383,];
		 	table[92] = [65434,33895,5428,57227,63987,13171,63421,24090,48960,41023,65535,491,57280,53213,29952,43987,];
		 	table[93] = [63683,61142,17405,47103,24239,16935,39852,63110,10199,63164,63367,13751,43725,57718,18919,58015,];
		 	table[94] = [21596,45042,11071,25048,64571,48056,65487,31613,49045,7392,32253,17407,24566,65534,54255,50382,];
		 	table[95] = [36278,44476,25564,4587,57177,9168,48820,62427,8167,56263,65379,64228,45611,25591,60731,44474,];
		 	table[96] = [65025,32511,65527,700,13055,61245,65532,32773,30715,48373,269,65527,65531,48954,87,57343,];
		 	table[97] = [61307,48509,56200,51412,65523,60796,24046,22271,32269,44127,65430,54655,16366,49472,28665,65511,];
		 	table[98] = [30619,36471,28351,58461,28623,24351,57471,65247,55259,510,65280,64379,65492,8159,63488,65535,];
		 	table[99] = [64399,123,48896,32604,65535,2035,60320,15847,63423,64471,65471,24579,65533,49133,61371,639,];
		 	table[100] = [65088,56829,65023,58105,26635,64287,64483,45053,40868,63469,31357,63503,61118,4053,47965,64927,];
		 	table[101] = [62171,15353,65151,60364,34666,29690,38396,40956,4255,64247,56759,48077,63614,60621,62310,15423,];
		 	table[102] = [65533,45119,59895,1662,38574,65030,54646,24535,16337,41971,52999,28599,40913,32580,31577,54237,];
		 	table[103] = [44859,43453,32207,65338,64480,63211,46081,65535,31482,47039,49152,4093,65407,65311,65276,38399,];
		 	table[104] = [0,46556,61283,16190,64383,27,59392,64502,40687,47327,65439,63,31696,62975,57307,16383,];
		 	table[105] = [65008,191,33824,48061,57143,65502,65389,4083,24652,24315,65531,64251,65118,537,31220,63966,];
		 	table[106] = [42999,60410,491,65332,60371,61299,45015,49216,29371,56575,61823,12248,47340,65035,56739,7947,];
		 	table[107] = [36637,18383,45355,65502,32750,55923,9471,52164,63325,52210,60669,46317,49145,19933,39389,64397,];
		 	table[108] = [47999,44923,56827,51545,64591,64181,45027,27999,65535,16253,30720,65499,46847,32511,64431,559,];
		 	table[109] = [65435,61383,65445,65535,7,50944,63487,65521,32765,447,56320,64956,49141,65535,65407,16127,];
		 	table[110] = [41,48640,63999,65407,28411,64894,52223,926,58112,64477,52479,63199,65535,4479,63488,64502,];
		 	table[111] = [59375,55100,65263,57327,49163,60863,65247,64973,31733,16637,65535,46943,65503,63792,64479,56471,];
		 	table[112] = [65267,49138,36831,57279,6015,60902,3967,13651,17532,34686,64018,17851,60896,30622,32791,49113,];
		 	table[113] = [32341,56969,49519,1095,31454,63325,22527,10501,34551,65173,38835,62255,53247,40821,29175,64279,];
		 	table[114] = [13550,60953,14284,61281,40918,61260,54927,64477,31603,28143,55294,42033,24191,38871,3931,65496,];
		 	table[115] = [40323,31694,8940,56575,30269,61319,57319,65005,20479,41212,15223,56316,15853,32732,28585,62832,];
		 	table[116] = [16379,11328,65407,33919,60503,57015,59036,61999,4075,54709,45035,60903,35887,65520,21375,59632,];
		 	table[117] = [47517,46591,65382,59279,55681,48656,40060,58305,40145,10035,3260,65389,64695,61367,41183,65535,];
		 	table[118] = [48907,65147,41983,13631,5068,38861,30263,64295,53206,32364,60496,60721,26492,64540,63226,24511,];
		 	table[119] = [4026,44591,41901,32766,64752,56948,65519,61952,64447,65186,15791,48383,63124,24505,62381,16271,];
		 	table[120] = [62060,40991,65519,447,30504,28677,65333,55811,54011,51194,16319,23581,65338,60467,47023,65180,];
		 	table[121] = [21046,31391,49146,59170,40951,64767,12219,46621,60678,7677,32215,61407,60195,61798,32473,3520,];
		 	table[122] = [15677,57279,51525,47747,32209,40400,31623,53107,40947,50165,57101,50686,3251,33538,59513,44736,];
		 	table[123] = [51059,28431,64893,2367,65521,343,25339,511,64948,15347,45075,17330,24275,65328,4095,60319,];
		 	table[124] = [65263,61955,16367,64393,14249,40601,57081,42796,14131,49654,33198,65086,23840,62199,54661,27095,];
		 	table[125] = [65535,65535,56071,65391,50431,55679,61390,48655,61819,61534,63183,65463,24311,61316,55243,3807,];
		 	table[126] = [65288,64767,60991,65535,5119,55295,44815,32765,48583,8186,0,0,0,0,0,0,];
		 	table[127] = [0,0,0,59200,48440,63795,32747,65261,32744,31862,46071,65519,65199,55479,65391,64447,];
		 	table[128] = [63739,56311,5970,58105,34248,30023,37008,58351,40692,16237,60974,1334,63420,32755,41083,32575,];
		 	table[129] = [1383,60256,47806,26113,64728,22591,51959,34783,49101,65440,23501,65215,46845,61351,30703,57244,];
		 	table[130] = [16311,63607,40231,47100,51893,57327,64346,61878,60473,61215,64447,32763,13,56062,48635,20095,];
		 	table[131] = [13311,23232,49141,40958,65471,95,0,65016,65482,28669,53245,40961,57343,64498,57279,65407,];
		 	table[132] = [65242,2063,47624,49151,31485,61143,64491,26617,57412,65427,57239,40791,65271,2271,57216,65247,];
		 	table[133] = [65477,63486,65531,26627,26619,27642,32767,24546,65535,65395,34783,59387,60413,63399,49022,61383,];
		 	table[134] = [7923,57218,30463,57214,31177,55933,61374,7835,31968,30715,34750,65531,7167,65499,16220,20448,];
		 	table[135] = [32767,24334,30719,56767,61519,65535,65535,4088,41918,64991,64540,65533,8061,64414,48639,57052,];
		 	table[136] = [16239,47867,57215,64495,32027,12012,44942,62199,31503,53230,7574,30662,65031,65525,55682,32735,];
		 	table[137] = [24294,51199,65262,31215,39510,65487,65119,56926,35182,63976,62558,59076,1,48764,15231,56799,];
		 	table[138] = [54685,59887,13484,56915,62835,19447,31567,40703,47358,18286,3579,65349,44029,64510,59863,56831,];
		 	table[139] = [60919,32767,56829,32491,53223,47103,48617,61329,23925,55164,0,0,0,0,0,0,];
		 	table[140] = [0,0,0,64128,65518,46321,49014,12271,46711,30655,40895,65533,38335,63150,30207,32571,];
		 	table[141] = [42997,2809,0,0,0,0,64464,11229,63027,39551,64939,55036,63974,49131,57311,62495,];
		 	table[142] = [42749,65535,19199,62331,32695,65273,46847,7516,32758,58879,8059,9220,48645,63902,56291,57330,];
		 	table[143] = [28655,65023,54905,52220,60413,61439,31,0,0,38912,57672,32791,27252,254,28031,65009,];
		 	table[144] = [47231,65267,57375,61814,61078,31551,60301,65533,44543,52147,34031,57727,19882,49136,48959,65087,];
		 	table[145] = [60415,65495,65503,53119,65531,34285,55103,1980,44799,65039,64943,30399,64239,14267,32732,41914,];
		 	table[146] = [46847,22263,24824,59359,65377,19679,45307,65349,32237,16378,8191,6908,65535,58287,51155,57219,];
		 	table[147] = [64343,61309,61439,4984,65216,24567,13499,24291,63245,61430,55294,191,62877,63479,20958,65504,];
		 	table[148] = [65225,895,24321,49135,40945,24743,61213,61951,15,0,0,0,0,0,0,0,];
		 	table[149] = [0,0,0,0,0,0,0,15488,64333,55583,31546,65251,16361,56447,63,0,];
		 	table[150] = [0,20480,62751,48647,64541,63771,48158,29183,28665,23486,22422,39707,32767,65532,34606,45031,];
		 	table[151] = [60405,62287,57341,59173,3036,23876,22343,64989,60735,30608,32127,35528,64250,62457,8234,61259,];
		 	table[152] = [62975,31183,43987,2981,63354,64399,36541,31,0,0,62208,64846,6743,34816,44716,30292,];
		 	table[153] = [6061,52735,65458,62511,23466,56319,2,0,0,29632,63978,11839,64142,48127,30396,65491,];
		 	table[154] = [61182,32370,32445,59383,63359,52989,4085,0,0,0,43264,56219,42183,37247,63690,32462,];
		 	table[155] = [32122,51175,52157,56494,64894,36726,37331,31987,485,19503,60791,41824,2011,24312,7671,8577,];
		 	table[156] = [27616,12444,15162,64222,32595,50165,25037,1978,0,0,0,0,0,0,9952,48894,];
		 	table[157] = [1017,60341,58221,59851,39983,49118,40835,43967,8183,65493,47071,57342,64942,65519,64382,61437,];
		 	table[158] = [43775,28351,0,0,0,0,0,46624,32717,48798,25267,22769,61709,64891,59889,48893,];
		 	table[159] = [50883,24429,65341,27135,65487,64500,56571,20471,8192,4407,21,0,0,0,0,0,];
		 	table[160] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[161] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[162] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[163] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[164] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[165] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[166] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[167] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[168] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[169] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[170] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[171] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[172] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[173] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[174] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[175] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[176] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[177] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[178] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[179] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[180] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[181] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[182] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[183] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[184] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[185] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[186] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[187] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[188] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[189] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[190] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[191] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[192] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[193] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[194] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[195] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[196] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[197] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[198] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[199] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[200] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[201] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[202] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[203] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[204] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[205] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[206] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[207] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[208] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[209] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[210] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[211] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[212] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[213] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[214] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[215] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[216] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[217] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[218] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[219] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[220] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[221] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[222] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[223] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[224] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[225] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[226] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[227] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[228] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[229] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[230] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[231] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[232] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[233] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[234] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[235] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[236] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[237] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[238] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[239] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[240] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[241] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[242] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[243] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[244] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[245] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[246] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[247] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
		 	table[248] = [65535,65535,65535,65535,511,0,0,0,0,0,0,0,0,0,0,256,];
		 	table[249] = [65535,65535,61439,65535,65535,65535,65535,65023,65535,65503,65535,65535,65535,65535,65407,65533,];
		 	table[250] = [16383,32485,15460,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[251] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[252] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[253] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
		 	table[254] = [0,0,0,65531,65055,65271,3967,0,0,0,0,0,0,0,0,0,];
		 	table[255] = [65534,65535,65535,65535,65535,32767,16,0,0,0,0,0,0,0,43,0,];

		 	var target = window.document.all(source).value;
		     	var cifcode = "";
		     	var big5list = "";
		     	
		     	for ( n = 0; n < target.length; n++){
		     		cifcode = target.charCodeAt(n);
		 		if ((cifcode >= 63223) && (cifcode <= 63391)){
		 			if ((cifcode >= 63223) && (cifcode <= 63305)) cifcode -= 50870; //
		 			else if ((cifcode >= 63306) && (cifcode <= 63391)) cifcode -= 50857; //
		 		}else if ( cifcode == 63219 ){
		 			cifcode = 12540; //
		 		}
		     	
		 		var check = (table[(Math.floor(cifcode / 256))])[(Math.floor(cifcode / 16) % 16)];
		 		if( !( ( check >> (cifcode % 16) ) & 1 == 1 )){
		     	    	big5list = big5list + target.charAt(n);
		     		}
		     	}
		 	if (big5list != ""){
		 		alert(big5list + " - 為難字 ,請以難字重新輸入 !"); 
		 		return false;
		 	}else
		 		return true;		
		 }
		 
		 
		 /*
			 * eLoan會有難字的部分(台新造字)，须於UI端新增附件程式碼. add by bolten(處理畫面上有一組相同name的問題)
			 */
			 function chkbig5BySameName(target){
			 	// by MS950字集（微軟版的BIG5）為準
			 	var table = new Array(256);
			 	table[0] = [65535,65535,65535,65535,65535,65535,65535,65535,1,0,59374,1983,65535,65535,65535,65535,];
			 	table[1] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[2] = [0,0,0,0,0,0,0,0,0,0,0,0,11904,512,0,0,];
			 	table[3] = [32,0,0,0,0,0,0,0,0,65534,1019,65534,1019,0,0,0,];
			 	table[4] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[5] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[6] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[7] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[8] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[9] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[10] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[11] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[12] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[13] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[14] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[15] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[16] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[17] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[18] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[19] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[20] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[21] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[22] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[23] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[24] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[25] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[26] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[27] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[28] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[29] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[30] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[31] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[32] = [0,13176,244,18476,0,0,0,0,0,0,4096,0,0,0,0,0,];
			 	table[33] = [552,0,0,0,0,0,1023,0,0,975,0,0,0,0,0,0,];
			 	table[34] = [0,50528,20009,48,0,4,203,0,0,544,32,32768,0,0,0,0,];
			 	table[35] = [128,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[36] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[37] = [4101,4369,4112,4112,0,65535,65535,31,65534,56,3,12300,51392,0,60,0,];
			 	table[38] = [608,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[39] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[40] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[41] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[42] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[43] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[44] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[45] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[46] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[47] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[48] = [65295,28727,1022,1,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[49] = [65504,65535,1023,0,0,0,0,0,0,65532,0,0,0,0,0,0,];
			 	table[50] = [0,0,65535,65535,15,0,0,0,65535,65535,65535,1,0,0,0,0,];
			 	table[51] = [0,0,0,0,0,0,0,0,49152,28672,2,0,16400,38,0,0,];
			 	table[52] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[53] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[54] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[55] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[56] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[57] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[58] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[59] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[60] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[61] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[62] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[63] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[64] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[65] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[66] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[67] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[68] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[69] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[70] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[71] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[72] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[73] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[74] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[75] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[76] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[77] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[78] = [65419,50035,26688,6927,59820,62284,512,49160,31068,51774,31094,1608,12255,63472,826,43263,];
			 	table[79] = [61239,9023,45060,64857,62410,65535,56991,65529,44031,32247,49152,36588,61119,65499,53251,17914,];
			 	table[80] = [64225,57342,49135,4267,65515,64682,61247,9469,30893,32630,61452,60927,53238,11514,63481,60267,];
			 	table[81] = [8189,38335,26231,49087,15355,65204,31662,4578,42625,16830,5173,29379,32112,29073,3,10091,];
			 	table[82] = [22475,28879,18226,3567,32474,64628,65030,48564,16287,35786,32329,22528,8847,60396,35420,56763,];
			 	table[83] = [61280,46823,41999,62099,14267,21662,53323,39855,50196,63444,12464,2580,12040,35024,65406,6447,];
			 	table[84] = [65498,64263,32753,31723,50671,16,39423,65023,31191,1383,65511,64971,50175,16448,28663,48526,];
			 	table[85] = [57338,1175,62656,23551,60795,53479,1150,63712,65439,46910,32254,34862,65533,48767,33790,63172,];
			 	table[86] = [62295,47357,54912,61309,22375,18312,65405,50143,61695,14249,32224,28924,16239,60570,19635,34433,];
			 	table[87] = [16286,56668,63245,18457,65187,7,44886,14591,38925,61368,16445,46944,55502,36917,29375,16383,];
			 	table[88] = [32759,31249,63419,44031,65280,28606,43324,65138,53231,61723,56171,64522,50150,61310,39836,62992,];
			 	table[89] = [61512,5876,65205,20866,51121,5563,28295,64479,58431,25549,49663,32382,65003,32095,30587,64766,];
			 	table[90] = [38411,56298,25129,21480,14303,65007,14069,48513,56344,64701,53988,65535,16343,65504,32623,45048,];
			 	table[91] = [39854,28377,62971,61717,31145,48635,23100,44463,56250,8108,29180,33657,31991,50015,57343,1383,];
			 	table[92] = [65434,33895,5428,57227,63987,13171,63421,24090,48960,41023,65535,491,57280,53213,29952,43987,];
			 	table[93] = [63683,61142,17405,47103,24239,16935,39852,63110,10199,63164,63367,13751,43725,57718,18919,58015,];
			 	table[94] = [21596,45042,11071,25048,64571,48056,65487,31613,49045,7392,32253,17407,24566,65534,54255,50382,];
			 	table[95] = [36278,44476,25564,4587,57177,9168,48820,62427,8167,56263,65379,64228,45611,25591,60731,44474,];
			 	table[96] = [65025,32511,65527,700,13055,61245,65532,32773,30715,48373,269,65527,65531,48954,87,57343,];
			 	table[97] = [61307,48509,56200,51412,65523,60796,24046,22271,32269,44127,65430,54655,16366,49472,28665,65511,];
			 	table[98] = [30619,36471,28351,58461,28623,24351,57471,65247,55259,510,65280,64379,65492,8159,63488,65535,];
			 	table[99] = [64399,123,48896,32604,65535,2035,60320,15847,63423,64471,65471,24579,65533,49133,61371,639,];
			 	table[100] = [65088,56829,65023,58105,26635,64287,64483,45053,40868,63469,31357,63503,61118,4053,47965,64927,];
			 	table[101] = [62171,15353,65151,60364,34666,29690,38396,40956,4255,64247,56759,48077,63614,60621,62310,15423,];
			 	table[102] = [65533,45119,59895,1662,38574,65030,54646,24535,16337,41971,52999,28599,40913,32580,31577,54237,];
			 	table[103] = [44859,43453,32207,65338,64480,63211,46081,65535,31482,47039,49152,4093,65407,65311,65276,38399,];
			 	table[104] = [0,46556,61283,16190,64383,27,59392,64502,40687,47327,65439,63,31696,62975,57307,16383,];
			 	table[105] = [65008,191,33824,48061,57143,65502,65389,4083,24652,24315,65531,64251,65118,537,31220,63966,];
			 	table[106] = [42999,60410,491,65332,60371,61299,45015,49216,29371,56575,61823,12248,47340,65035,56739,7947,];
			 	table[107] = [36637,18383,45355,65502,32750,55923,9471,52164,63325,52210,60669,46317,49145,19933,39389,64397,];
			 	table[108] = [47999,44923,56827,51545,64591,64181,45027,27999,65535,16253,30720,65499,46847,32511,64431,559,];
			 	table[109] = [65435,61383,65445,65535,7,50944,63487,65521,32765,447,56320,64956,49141,65535,65407,16127,];
			 	table[110] = [41,48640,63999,65407,28411,64894,52223,926,58112,64477,52479,63199,65535,4479,63488,64502,];
			 	table[111] = [59375,55100,65263,57327,49163,60863,65247,64973,31733,16637,65535,46943,65503,63792,64479,56471,];
			 	table[112] = [65267,49138,36831,57279,6015,60902,3967,13651,17532,34686,64018,17851,60896,30622,32791,49113,];
			 	table[113] = [32341,56969,49519,1095,31454,63325,22527,10501,34551,65173,38835,62255,53247,40821,29175,64279,];
			 	table[114] = [13550,60953,14284,61281,40918,61260,54927,64477,31603,28143,55294,42033,24191,38871,3931,65496,];
			 	table[115] = [40323,31694,8940,56575,30269,61319,57319,65005,20479,41212,15223,56316,15853,32732,28585,62832,];
			 	table[116] = [16379,11328,65407,33919,60503,57015,59036,61999,4075,54709,45035,60903,35887,65520,21375,59632,];
			 	table[117] = [47517,46591,65382,59279,55681,48656,40060,58305,40145,10035,3260,65389,64695,61367,41183,65535,];
			 	table[118] = [48907,65147,41983,13631,5068,38861,30263,64295,53206,32364,60496,60721,26492,64540,63226,24511,];
			 	table[119] = [4026,44591,41901,32766,64752,56948,65519,61952,64447,65186,15791,48383,63124,24505,62381,16271,];
			 	table[120] = [62060,40991,65519,447,30504,28677,65333,55811,54011,51194,16319,23581,65338,60467,47023,65180,];
			 	table[121] = [21046,31391,49146,59170,40951,64767,12219,46621,60678,7677,32215,61407,60195,61798,32473,3520,];
			 	table[122] = [15677,57279,51525,47747,32209,40400,31623,53107,40947,50165,57101,50686,3251,33538,59513,44736,];
			 	table[123] = [51059,28431,64893,2367,65521,343,25339,511,64948,15347,45075,17330,24275,65328,4095,60319,];
			 	table[124] = [65263,61955,16367,64393,14249,40601,57081,42796,14131,49654,33198,65086,23840,62199,54661,27095,];
			 	table[125] = [65535,65535,56071,65391,50431,55679,61390,48655,61819,61534,63183,65463,24311,61316,55243,3807,];
			 	table[126] = [65288,64767,60991,65535,5119,55295,44815,32765,48583,8186,0,0,0,0,0,0,];
			 	table[127] = [0,0,0,59200,48440,63795,32747,65261,32744,31862,46071,65519,65199,55479,65391,64447,];
			 	table[128] = [63739,56311,5970,58105,34248,30023,37008,58351,40692,16237,60974,1334,63420,32755,41083,32575,];
			 	table[129] = [1383,60256,47806,26113,64728,22591,51959,34783,49101,65440,23501,65215,46845,61351,30703,57244,];
			 	table[130] = [16311,63607,40231,47100,51893,57327,64346,61878,60473,61215,64447,32763,13,56062,48635,20095,];
			 	table[131] = [13311,23232,49141,40958,65471,95,0,65016,65482,28669,53245,40961,57343,64498,57279,65407,];
			 	table[132] = [65242,2063,47624,49151,31485,61143,64491,26617,57412,65427,57239,40791,65271,2271,57216,65247,];
			 	table[133] = [65477,63486,65531,26627,26619,27642,32767,24546,65535,65395,34783,59387,60413,63399,49022,61383,];
			 	table[134] = [7923,57218,30463,57214,31177,55933,61374,7835,31968,30715,34750,65531,7167,65499,16220,20448,];
			 	table[135] = [32767,24334,30719,56767,61519,65535,65535,4088,41918,64991,64540,65533,8061,64414,48639,57052,];
			 	table[136] = [16239,47867,57215,64495,32027,12012,44942,62199,31503,53230,7574,30662,65031,65525,55682,32735,];
			 	table[137] = [24294,51199,65262,31215,39510,65487,65119,56926,35182,63976,62558,59076,1,48764,15231,56799,];
			 	table[138] = [54685,59887,13484,56915,62835,19447,31567,40703,47358,18286,3579,65349,44029,64510,59863,56831,];
			 	table[139] = [60919,32767,56829,32491,53223,47103,48617,61329,23925,55164,0,0,0,0,0,0,];
			 	table[140] = [0,0,0,64128,65518,46321,49014,12271,46711,30655,40895,65533,38335,63150,30207,32571,];
			 	table[141] = [42997,2809,0,0,0,0,64464,11229,63027,39551,64939,55036,63974,49131,57311,62495,];
			 	table[142] = [42749,65535,19199,62331,32695,65273,46847,7516,32758,58879,8059,9220,48645,63902,56291,57330,];
			 	table[143] = [28655,65023,54905,52220,60413,61439,31,0,0,38912,57672,32791,27252,254,28031,65009,];
			 	table[144] = [47231,65267,57375,61814,61078,31551,60301,65533,44543,52147,34031,57727,19882,49136,48959,65087,];
			 	table[145] = [60415,65495,65503,53119,65531,34285,55103,1980,44799,65039,64943,30399,64239,14267,32732,41914,];
			 	table[146] = [46847,22263,24824,59359,65377,19679,45307,65349,32237,16378,8191,6908,65535,58287,51155,57219,];
			 	table[147] = [64343,61309,61439,4984,65216,24567,13499,24291,63245,61430,55294,191,62877,63479,20958,65504,];
			 	table[148] = [65225,895,24321,49135,40945,24743,61213,61951,15,0,0,0,0,0,0,0,];
			 	table[149] = [0,0,0,0,0,0,0,15488,64333,55583,31546,65251,16361,56447,63,0,];
			 	table[150] = [0,20480,62751,48647,64541,63771,48158,29183,28665,23486,22422,39707,32767,65532,34606,45031,];
			 	table[151] = [60405,62287,57341,59173,3036,23876,22343,64989,60735,30608,32127,35528,64250,62457,8234,61259,];
			 	table[152] = [62975,31183,43987,2981,63354,64399,36541,31,0,0,62208,64846,6743,34816,44716,30292,];
			 	table[153] = [6061,52735,65458,62511,23466,56319,2,0,0,29632,63978,11839,64142,48127,30396,65491,];
			 	table[154] = [61182,32370,32445,59383,63359,52989,4085,0,0,0,43264,56219,42183,37247,63690,32462,];
			 	table[155] = [32122,51175,52157,56494,64894,36726,37331,31987,485,19503,60791,41824,2011,24312,7671,8577,];
			 	table[156] = [27616,12444,15162,64222,32595,50165,25037,1978,0,0,0,0,0,0,9952,48894,];
			 	table[157] = [1017,60341,58221,59851,39983,49118,40835,43967,8183,65493,47071,57342,64942,65519,64382,61437,];
			 	table[158] = [43775,28351,0,0,0,0,0,46624,32717,48798,25267,22769,61709,64891,59889,48893,];
			 	table[159] = [50883,24429,65341,27135,65487,64500,56571,20471,8192,4407,21,0,0,0,0,0,];
			 	table[160] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[161] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[162] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[163] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[164] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[165] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[166] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[167] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[168] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[169] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[170] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[171] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[172] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[173] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[174] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[175] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[176] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[177] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[178] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[179] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[180] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[181] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[182] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[183] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[184] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[185] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[186] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[187] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[188] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[189] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[190] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[191] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[192] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[193] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[194] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[195] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[196] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[197] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[198] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[199] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[200] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[201] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[202] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[203] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[204] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[205] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[206] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[207] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[208] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[209] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[210] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[211] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[212] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[213] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[214] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[215] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[216] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[217] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[218] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[219] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[220] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[221] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[222] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[223] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[224] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[225] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[226] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[227] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[228] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[229] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[230] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[231] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[232] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[233] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[234] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[235] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[236] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[237] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[238] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[239] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[240] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[241] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[242] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[243] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[244] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[245] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[246] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[247] = [65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,65535,];
			 	table[248] = [65535,65535,65535,65535,511,0,0,0,0,0,0,0,0,0,0,256,];
			 	table[249] = [65535,65535,61439,65535,65535,65535,65535,65023,65535,65503,65535,65535,65535,65535,65407,65533,];
			 	table[250] = [16383,32485,15460,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[251] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[252] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[253] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,];
			 	table[254] = [0,0,0,65531,65055,65271,3967,0,0,0,0,0,0,0,0,0,];
			 	table[255] = [65534,65535,65535,65535,65535,32767,16,0,0,0,0,0,0,0,43,0,];

			 	//var target = window.document.all(source).value;
			     	var cifcode = "";
			     	var big5list = "";
			     	for ( n = 0; n < target.length; n++){
			     		cifcode = target.charCodeAt(n);
			 		if ((cifcode >= 63223) && (cifcode <= 63391)){
			 			if ((cifcode >= 63223) && (cifcode <= 63305)) cifcode -= 50870; //
			 			else if ((cifcode >= 63306) && (cifcode <= 63391)) cifcode -= 50857; //
			 		}else if ( cifcode == 63219 ){
			 			cifcode = 12540; //
			 		}
			     	
			 		var check = (table[(Math.floor(cifcode / 256))])[(Math.floor(cifcode / 16) % 16)];
			 		if( !( ( check >> (cifcode % 16) ) & 1 == 1 )){
			     	    	big5list = big5list + target.charAt(n);
			     		}
			     	}
			 	if (big5list != ""){
			 		alert(big5list + " - 為難字 ,請以難字重新輸入 !"); 
			 		return false;
			 	}else
			 		return true;		
			 }
			 
			// 難字檢核  驗證所有text和 textArea add by bolten
				function checkAllBig5(formId) {
					var form = document.forms[formId];
					var result = true;
					for (var i=0; i<form.elements.length; i++) {
						var element = form.elements[i];
						if (element.type == 'text'||element.type == 'textarea') {
							if (element.disabled == false && element.readOnly == false && !isEmpty(element.value)) {
								result = chkbig5BySameName(element.value);
								if (result == false) {
									element.select();
									element.focus();
									break;
								}
							}
						}
					}
					return result;
				}
			//修改checkbox value為Y或N，依據勾選狀態。
			function setCheckboxValueOnClick(checkbox){
				if(checkbox.checked){
					checkbox.value = 'Y';
				}else{
					checkbox.value = 'N';
				}
			}
			//非负整数（正整数 + 0）
			function checkIsPositiveInteger(amount){
				var regu = /^\d+$/;
				amount = amount.replace(new RegExp(",","gm"),"");
				if (regu.test(amount)){
					return true;
				}else{
					return false;
				}
			}
			//檢核是否為重覆
			function isDuplicateWithSameName(elmName){					 
				var elms = document.getElementsByName(elmName);
				if(elms!=null&&elms.length>0){
					var elmValue="";
					var compareValue="";
					for(var i=0;i<elms.length;i++){
						if(elms[i]!=null&&elms[i].disabled!=true){
							elmValue="";
							if (elms[i].type=="text") {
								elmValue=elms[i].value;
						    }else if(elms[i].type=="select-one"){
						    	elmValue=getSelectValue(elms[i]);						    	
						    }						     
							if(isEmpty(elmValue)) continue;

							for(var k=i+1;k<elms.length;k++){								
								if(elms[k]!=null&&elms[k].disabled!=true){
									compareValue="";
									if (elms[k].type=="text") {
										compareValue=elms[k].value;
									}
									else if(elms[k].type=="select-one"){
										compareValue=getSelectValue(elms[k]);	
									}
									if(elmValue==compareValue) return true;										 	
								}
							}
					 	
						}
					}
				}
				return false; 
			}	
			
			//取得日期字符串,返回YYYY/MM/DD YYYY/MM/DD HH:MM:SS
			function getDate(date,fomatCode){
			     var thisYear = date.getYear();   
			     var thisMonth = date.getMonth() + 1;   
			     //如果月份长度是一位则前面补0   
			     if(thisMonth<10) thisMonth = "0" + thisMonth; 
			     var thisDay = date.getDate();   
			     //如果天的长度是一位则前面补0   
			     if(thisDay<10) thisDay = "0" + thisDay;
			     
			     var thisHour = date.getHours();
			     //如果天的长度是一位则前面补0   
			     if(thisHour<10) thisHour = "0" + thisHour;
			     
			     var thisMinutes = date.getMinutes(); 
			     //如果天的长度是一位则前面补0   
			     if(thisMinutes<10) thisMinutes = "0" + thisMinutes;
			     
			     var thisSec = date.getSeconds();
			     //如果天的长度是一位则前面补0   
			     if(thisSec<10) thisSec = "0" + thisSec;
			     if(fomatCode=='YYYY/MM/DD'){
			    	 return thisYear + "/" + thisMonth + "/" + thisDay;   
			     }
			     if(fomatCode=='YYYY/MM/DD HH:MM:SS'){
			    	 return thisYear + "/" + thisMonth + "/" + thisDay+" "+thisHour+":"+thisMinutes+":"+thisSec;  
			     }
			}
			
			
			/**
			 * validate value'lengtth of input (one input of element's name)
			 * @param element's name
			 * @param length
			 * @param error message
			 * @param allow null
			 * */
			function checkLengthOne(elmName,length,msg,allowNull){
				var elm = document.getElementById(elmName);
			    var value = elm.value;
			    var oldLength=value.length;
			    var byteLength=oldLength
			    for (var i=0;i<oldLength;i++)
			    {
			        if (value.charCodeAt(i)<0||value.charCodeAt(i)>255) byteLength++
			    }
			    if(allowNull){
			    	if(byteLength>length){
				    	alert(msg);
						showError(elm);
				        return false;
				    }
			    }else{
				    if(byteLength<=0||byteLength>length){
				    	alert(msg);
						showError(elm);
				        return false;
				    }
			    }
			    return true;
			}

			/**
			 * validate value'lengtth of input (more than two input of element's name)
			 * @param element's name
			 * @param length
			 * @param error message
			 * @param allow null
			 * */
			function checkLengthMore(elmName,length,msg,allowNull){
				var elms = document.getElementsByName(elmName);
				var elm;
				var elmValue;
				var oldLength;
				var byteLength;
				if(elms!=null&&elms.length>0){
					for(var i=0;i<elms.length;i++){
						elmValue="";
						oldLength=0;
						byteLength=0;
						elm = elms[i];
						if(elm.disabled!=true){
							elmValue = elm.value;
							oldLength = elmValue.length;
							byteLength=oldLength
						    for (var j=0;j<oldLength;j++)
						    {
						        if (elmValue.charCodeAt(j)<0||elmValue.charCodeAt(j)>255) byteLength++
						    }
						    if(allowNull){
						    	if(byteLength>length){
							    	alert(msg);
									showError(elm);
							        return false;
							    }
						    }else{
							    if(byteLength<=0||byteLength>length){
							    	alert(msg);
									showError(elm);
							        return false;
							    }
						    }
						}
					}
				}
				return true;
			}
			 
		 	//ex:判斷某個欄位輸入的數值的長度是否小於規定長度,(其中,漢字為雙字節)
			//elmName,檢驗的欄位的ID;length,可輸入的最大長度;msg,超出要求后給予提示;allowNull,是否可為空,true,false;
			function checkTextareLength(elmName,length,msg,allowNull){
				var elm = document.getElementById(elmName);
			    var value = elm.value;
			    var oldLength=value.length;
			    var byteLength=oldLength;
			    for (var i=0;i<oldLength;i++)
			    {
			        if (value.charCodeAt(i)<0||value.charCodeAt(i)>255) byteLength++;
			    }
			    if(allowNull){
			    	if(byteLength>length){
				    	alert(msg);
				    	elm.focus();
				        return false;
				    }
			    }else{
				    if(byteLength<=0||byteLength>length){
				    	alert(msg);
				    	elm.focus();
				        return false;
				    }
			    }
			    return true;
			}
		
			//EvanLai Add 2010/08/04
			//檢測輸入的必須為數字且不能帶小數點和逗號.  
			//如果不為數字那麼 return false,為數字則返回true;
			function checkIsNaN(value){
				if(!isEmpty(value)){
					return value.match(new RegExp("^[0-9]+$")); 
				}
				return true;
			}
			/*
			 * 檢查漢字，全角的長度。
			 * obj txtObject, 
			 * len 最大長度, 
			 * msg 錯誤訊息
			 */
			function checkLengthForCharacter(obj, len, msg) {
				var str = obj.value;
				var aMatch = str.match(/[^\x00-\x80]/g);
				var lengthChar = str.length + (!aMatch?0:aMatch.length);
				if(lengthChar > len){
					alert(msg);
					obj.focus();
					return false;
				}else{
					return true;
				}
			}
			
			function checkLength(obj,len,msg){
				var str = obj.value;
				var aMatch = RegExp(/^[A-Za-z]+$/);
				if(!aMatch.test(str)){
					if(str.length>len){
						alert(msg);
						obj.focus();
						return false;
					}
				}
				return true;
			}
			 
			function showError(elt){
				if(elt!=null){
					elt.focus();
					if(elt.type!="select-one"){
						elt.select();
					}
				}
			}
			
			/**
			 * 設置該標簽下的所有 input
			 * @param element
			 * @return
			 */
			function setInputReadOnlyOrDisable(element){
				var loopLength = element.childNodes.length; 
				for (var loopIndex = 0; loopIndex < loopLength; loopIndex++){ 
					var currentNodeObj = element.childNodes[loopIndex]; 
					if(currentNodeObj != null) {
						if(currentNodeObj.type=="text") {
							currentNodeObj.readOnly = true;
						} else if(currentNodeObj.type=="checkbox") {
							currentNodeObj.onclick = function() { return false; }; 
						} else if(currentNodeObj.type=="button") {
							currentNodeObj.disabled = true;
						} else if(currentNodeObj.type=="radio") {
							setReadOnly(currentNodeObj);
						} else if(currentNodeObj.type=="select-one"){
							setReadOnly(currentNodeObj);
						} else if(currentNodeObj.type=="textarea"){				    	 
							currentNodeObj.readOnly = true;
						  }
						if (currentNodeObj.nodeType == 1){ 
							setInputReadOnlyOrDisable(currentNodeObj); 
						} 
					}
				 } 
			}  
			/**
			 * 設置畫面的高度，解決邊框不能對齊的問題。
			 */
			function setWinHeight(){
				//main_block
				var mainBlockDIV = document.getElementById("main_block");
				//display_bg
				var displayBgDIV = document.getElementById("display_bg");
				//mainbody_bg
				var mainbodyBgDIV = document.getElementById("mainbody_bg");
				//op_title_name
				//var opTitleNameDIV = document.getElementById("op_title_name");
				//mainbody_bgWhite
				//var mainbodyBgWhiteDIV = document.getElementById("mainbody_bgWhite");
				if(mainBlockDIV!=null){
					mainBlockDIV.style.height = (mainBlockDIV.scrollHeight+50)+"px";
				}
				if(displayBgDIV!=null){
					displayBgDIV.style.height = (displayBgDIV.scrollHeight+50)+"px";
				}
				if(mainbodyBgDIV!=null){
					mainbodyBgDIV.style.height = (mainbodyBgDIV.scrollHeight+65)+"px";
				}
				//opTitleNameDIV.style.height = opTitleNameDIV.scrollHeight+"px";
				//mainbodyBgWhiteDIV.style.height = mainbodyBgWhiteDIV.scrollHeight+"px";
			}
			//show出輸入退件原因的畫面
			function initRejectPage(ucNo,actionId,formId,rejectAction){
				var objForm = document.getElementById(formId);
				var formAction = objForm.action;
				var rejectActionParam = "";
				if(rejectAction!=undefined && !isEmpty(rejectAction)){
					rejectActionParam = '&rejectAction='+rejectAction;
				}
				var contents = document.getElementById("contents");
				if(contents!=null && contents!=undefined){
					if(!isEmpty(contents.value)){
						rejectActionParam += '&contents='+contents.value;
					}
				}
				var rpId = document.getElementById("rpId");
				if(rpId!=null && rpId!=undefined){
					if(!isEmpty(rpId.value)){
						rejectActionParam += '&rpId='+rpId.value;
					}
				}
				window.open(formAction+'?ucNo='+ucNo+'&actionId='+actionId+'&formAction='+formAction+rejectActionParam,'closeflag','status=no,toolbar=yes,menubar=no,location=no,scrollbars=yes,resizable=no,width=400px,height=500px');
			}
			//将数字转换为千分位显示
			function toMillonNum(myNum){
				var s=parseFloat(myNum); //获取小数型数据
				s+="";
				if(s.indexOf(".")==-1) s+=".0";//如果没有小数点，在后面补个小数点和0
				if(/\.\d$/.test(s)) s+="0";   //正则判断
				while(/\d{4}(\.|,)/.test(s)){ //符合条件则进行替换
					s=s.replace(/(\d)(\d{3}(\.|,))/,"$1,$2");//每隔3位添加一个，
				}
				return s
			}
			
			//對位登打
			function contraposition(x1, y1, x2, y2) {
				  //this.id     = id;
				  this.x      = x1;
				  this.y      = y1;
				  this.width  = (x2-x1);
				  this.height = (y2-y1);
				  this.annotationPosition = null;
			}

			function annotationPosition(x, y) {
				  this.x = x;
				  this.y = y;
			}
			
			//起控制作用的控件(如radio、check box)的值變事件
			function changeCtlValue(ctlObj,splitFlag){
				var valueObject = document.getElementById(ctlObj.name.replace("_ctl",""));
				var oldValue = valueObject.value;
				if(ctlObj.checked){
					if(ctlObj.type=="radio")
						valueObject.value = ctlObj.value;
					else
						valueObject.value = oldValue+splitFlag + ctlObj.value + splitFlag;
				}else{
					if(ctlObj.type=="radio")
						valueObject.value = "";
					else
						valueObject.value = oldValue.replace(splitFlag+ctlObj.value+splitFlag,splitFlag);
				}
				if(ctlObj.type!="radio"){
					valueObject.value = valueObject.value.replace(splitFlag+splitFlag,splitFlag);
					if(valueObject.value==splitFlag)
						valueObject.value="";
				}
				
			}
		/**
		*  disable all input fields.
		**/
		function disablePageAllFields(objForm,arrobj){
			     fobj=objForm.elements;
				 for (i=0;i<fobj.length;i++) {
					 if (fobj[i].type=="text") {
				    	 objForm.elements[i].disabled= true;
				     }
				     if (fobj[i].type=="select-one"){
				    	 objForm.elements[i].disabled=true;
				     }
				     if (fobj[i].type=="checkbox"){
				    	 objForm.elements[i].disabled=true;
				     }	
				     if (fobj[i].type=="textarea"){				    	 
				    	 objForm.elements[i].disabled=true;
				     }
				     if (fobj[i].type=="radio"){
				    	 objForm.elements[i].disabled=true;
				     }
				     if(fobj[i].type=="button"){
				    	 objForm.elements[i].style.display="none";
				     } 
				 }
				//img標籤
				 var imgs = document.getElementsByTagName("img"); 
				 for(m=0;m<imgs.length;m++){
					 imgs[m].style.display="none";
				 }
				//image標籤
				 var images = document.getElementsByTagName("image"); 
				 for(k=0;k<images.length;k++){
					 images[k].style.display="none";
				 }
				 for(j=0;j<arrobj.length;j++){
					 if (arrobj[j]==null){
						 continue;
					 }
					 if (arrobj[j].type=="text") {
						 arrobj[j].disabled= false;
				     }
				     if (arrobj[j].type=="select-one"){
				    	 arrobj[j].disabled=false;
				     }
				     if (arrobj[j].type=="checkbox"){
				    	 arrobj[j].disabled=false;
				     }	
				     if (arrobj[j].type=="textarea"){	
				    	 arrobj[j].disabled=false;
				     }
				     if (arrobj[j].type=="radio"){
				    	 arrobj[j].disabled=false;
				     }
				     if(arrobj[j].type=="button"){
				    	 arrobj[j].style.display="inline";
				     } 
				 }
		}
		
		/**
		* 回清單
		**/
		function goBackToWorkList(formId,useCaseNo, actionId){
			if(confirm("確認回清單?")){
				var objForm = document.getElementById(formId);
				actionClicked(objForm, useCaseNo, '', actionId);
			}
		}
		/**
		* 徹件
		**/
		function rejectCase(formId,useCaseNo, actionId){
			if(confirm("確認撤件?")){
				var objForm = document.getElementById(formId);
				actionClicked(objForm, useCaseNo, '', actionId);
			}
		}
		//重新送出
		function replyMoreInformation(formId,useCaseNo, actionId,isNeedDesc){
			var replyReason = document.getElementById("contents");
			if(isNeedDesc&&isEmpty(replyReason.value)){
				alert("請輸入回覆說明！");
				replyReason.focus();
				return false;
			}
			var objForm = document.getElementById(formId);
			actionClicked(objForm, useCaseNo, '', actionId);
		}
		
		function readOnlyPageAllFields(objForm,arrobj){
		     fobj=objForm.elements;
			 for (i=0;i<fobj.length;i++) {
				 if (fobj[i].type=="text") {
					 fobj[i].readOnly= "readOnly";
			     }
			     if (fobj[i].type=="select-one"){
			    	 setReadOnly(fobj[i]);
			     }
			     if (fobj[i].type=="checkbox"){
			    	 fobj[i].readOnly= "readOnly";
			     }	
			     if (fobj[i].type=="textarea"){				    	 
			    	 fobj[i].readOnly= "readOnly";
			     }
			     if(fobj[i].type=="button"){
			    	 fobj[i].style.display="none";
			     } 
			 }
			//img標籤
			 var imgs = document.getElementsByTagName("img"); 
			 for(m=0;m<imgs.length;m++){
				 imgs[m].style.display="none";
			 }
			//image標籤
			 var images = document.getElementsByTagName("image"); 
			 for(k=0;k<images.length;k++){
				 images[k].style.display="none";
			 }
			 for(j=0;j<arrobj.length;j++){
				 if (arrobj[j]==null){
					 continue;
				 }
				 if (arrobj[j].type=="text") {
					 arrobj[j].readOnly= "";
			     }
				 if (arrobj[j].type=="select-one"){
					 removeReadOnly(arrobj[j]);
			     }
			     if (arrobj[j].type=="checkbox"){
			    	 arrobj[j].readOnly= "";
			     }	
			     if (arrobj[j].type=="textarea"){	
			    	 arrobj[j].readOnly= "";
			     }
			     if (arrobj[j].type=="radio"){
			    	 removeReadOnly(arrobj[j]);
			     }
			     if(arrobj[j].type=="button"){
			    	 arrobj[j].style.display="inline";
			     } 
			 }
	}
		/**
		 * 查找html畫面中的控件
		 * @param objectName 控件名
		 * @param currentDocument 預查找的Document對象
		 * @returns 控件object
		 */
		function findObj(objectName, currentDocument){
		    var p, i, foundObj;
		   
		    if(!currentDocument) currentDocument = document;
		    if( (p = objectName.indexOf("?")) > 0 && parent.frames.length)
		    {
		      currentDocument = parent.frames[objectName.substring(p+1)].document;
		      objectName = objectName.substring(0,p);
		    }
		    if(!(foundObj = currentDocument[objectName]) && currentDocument.all) foundObj = currentDocument.all[objectName];
		    for (i=0; !foundObj && i < currentDocument.forms.length; i++)
		      foundObj = currentDocument.forms[objectName];
		    for(i=0; !foundObj && currentDocument.layers && i < currentDocument.layers.length; i++)
		      foundObj = findObj(objectName,currentDocument.layers.document);
		    if(!foundObj && document.getElementById) foundObj = document.getElementById(objectName);
		   
		    return foundObj;
		}

		
		function disableFields(objForm){
		     fobj=objForm.elements;
			 for (i=0;i<fobj.length;i++) {     
			     if (fobj[i].type=="text") {
			    	 objForm.elements[i].disabled= true;
			     }
			     if (fobj[i].type=="select-one"){
			    	 objForm.elements[i].disabled=true;
			     }
			     if (fobj[i].type=="checkbox"){
			    	 objForm.elements[i].disabled=true;
			     }	
			     if (fobj[i].type=="textarea"){				    	 
			    	 objForm.elements[i].readOnly=true;
			     }
			     if (fobj[i].type=="radio"){
			    	 objForm.elements[i].disabled=true;
			     }
			 }		    
		}
		
		function enableALLFields(objForm){
		     fobj=objForm.elements;
			 for (i=0;i<fobj.length;i++) {     
			     if (fobj[i].type=="text") {
			    	 objForm.elements[i].disabled= false;
			     }
			     if (fobj[i].type=="select-one"){
			    	 objForm.elements[i].disabled=false;
			     }
			     if (fobj[i].type=="checkbox"){
			    	 objForm.elements[i].disabled=false;
			     }	
			     if (fobj[i].type=="textarea"){				    	 
			    	 objForm.elements[i].readOnly=false;
			     }
			     if (fobj[i].type=="radio"){
			    	 objForm.elements[i].disabled=false;
			     }
			 }		    
		}
		//變更選中tab的css
		function unSelectedAllSpans() {
			document.getElementById("editApplication").className = "TabStyle_UnSelected";
			document.getElementById("telephoneVerificationt").className = "TabStyle_UnSelected";
			document.getElementById("creditReview").className = "TabStyle_UnSelected";
			document.getElementById("amountCalculationConfirmSpan").className = "TabStyle_UnSelected";
			document.getElementById("aoConfirm").className = "TabStyle_UnSelected";
			document.getElementById("appealSalesManagerApproval").className = "TabStyle_UnSelected";
			document.getElementById("trustToPersonalInfoVerification").className = "TabStyle_UnSelected";
			document.getElementById("imageFundingAmountManagerApproval").className = "TabStyle_UnSelected";
			document.getElementById("fundingApproval").className = "TabStyle_UnSelected";
		}