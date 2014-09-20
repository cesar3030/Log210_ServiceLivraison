
$("SubmitForm").on('submit',function(){
		   if($('#contact-password1').val()!=$('#contact-password2').val())
		   {
		       alert('Les deux mot de passe ne sont pas identiques');
		       return false;
		   }
		   alert('hep');
		   return true;
	});