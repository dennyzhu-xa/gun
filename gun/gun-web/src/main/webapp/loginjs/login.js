var login;
Ext.onReady(function() {  
	
    var userLoginPanel = Ext.create('Ext.panel.Panel', {  
        bodyStyle:'background:url('+appBaseUri+'images/timg.jpg) center top no-repeat #FFF;background-size:450px 100px;',
        border : false,  
        defaults:{  
            margin:'58 0'  
        },  
        items:{  
            xtype : 'tabpanel',  
            id : 'loginTabs',  
            activeTab : 0,  
            height : 180,  
            border : false,  
            items:[{  
                title : "Identity Authentication ",  
                style:'color:#fff',
                xtype : 'form', 
                url:'login.do', 
                id : 'loginForm',  
                defaults : {  
                    width : 260,  
                    margin: '10 0 0 70'  
                },  
                // The fields  
                defaultType : 'textfield',  
                labelWidth : 40,  
                items: [{  
                    fieldLabel: 'Account',  
                    cls : 'user',  
                    name: 'userName',  
                    labelAlign:'right',  
                    labelWidth:85,  
                    maxLength : 15,  
                    emptyText:'Please enter account',  
                    blankText:"The account is required！",//错误提示信息，默认为This field is required!  
                    allowBlank: false  
                },{  
                    fieldLabel: 'Password',  
                    cls : 'key',  
                    name: 'passWord',  
                    inputType:"password",  
                    labelWidth:85,  
                    labelAlign:'right',  
                    emptyText:'Please enter password',  
                    maxLength : 15,  
                    blankText:"The password is required！",//错误提示信息，默认为This field is required!  
                    allowBlank: false  
                }]  
            },{  
                title : 'Forgot password ? ',  
                contentEl : 'aboutDiv',  
                defaults : {  
                    width : 230 
                } 
            }]  
        }  
    });  
      
    login = Ext.create('Ext.window.Window', {  
        title : 'Lottery Management system',  
        id : 'loginWindow',
        width : 440,  
        height : 300,  
        layout: 'fit',
        plain : true,  
        modal : false,  
        maximizable : false,  
        draggable : false,  
        closable : false,  
        resizable : false,  
        items: userLoginPanel,  
        // 重置 和 登录 按钮.  
        buttons: [{  
            text: 'Reset',  
            iconCls : 'icon-arrow-undo',  
            handler: function() {  
            	Ext.getCmp('loginForm').form.reset();
            }  
        }, {  
            text: 'Login',  
            iconCls : 'icon-user-go',  
            handler: function() {  
                 loginSubmit();  
            }  
        }]  
    }).show();  
    
    new Ext.KeyMap(document, {    
        key: Ext.EventObject.ENTER ,  // Enter    
        fn: function()    
        {    
        	loginSubmit();  
        },    
        scope: this    
    }); 
    
}); 

function loginSubmit(){
	var formPanl = Ext.getCmp('loginForm');
	if(formPanl.isValid()){//判断是否通过验证  
		formPanl.form.doAction('submit',{
		method:"POST",
		waitMsg:"Login, please wait a moment...",
		success: function(form1, action) {
			if(action.result.result==-1){
				formPanl.form.findField('userName').setValue('');
				formPanl.form.findField('passWord').setValue('');
				Ext.Msg.alert('prompt', "Login failed. User name does not exist!");
				return;
			}
			if(action.result.result==-2){
				formPanl.form.findField('passWord').setValue('');
				Ext.Msg.alert('prompt', "Login failed. password error!");
				return;
			}
			window.location.href="firstPageInit.do";
			Ext.Msg.wait("Login, please wait a moment..."); 
          },
        failure: function(form1, action) {
        	formPanl.form.findField('userName').setValue('');
        	formPanl.form.findField('passWord').setValue('');
             Ext.Msg.alert('prompt', "Login failed!");
          }});
	}  
} 
