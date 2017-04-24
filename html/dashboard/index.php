<?php 
require_once ($_SERVER['DOCUMENT_ROOT'].'/api/user.php');
?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
	<script src="/_include/pace/pace.min.js"></script>
	<link href="/_include/pace/themes/flash.css" rel="stylesheet" />

    <title>Home - Dashboard - Discord Groups</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        
    <!-- Custom styles for this template -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/style-responsive.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
	<?php if(!isset($_COOKIE['discord_groups'])){ 
		header('Location: https://discordapp.com/oauth2/authorize?client_id=280192562583699458&scope=guilds+identify&response_type=code');
	} else {
		$tolken = $_COOKIE['discord_groups'];
		$result = UserApi::getDiscord($tolken);
		if($result['status'] == "rows"){
			?>
		<div id="login-page">
          <div class="container">
          
              <form class="form-login">
                <h2 class="form-login-heading">Invalid</h2>
                <div class="login-social-link centered">
                    <p>You have an invalid login.</p>
                    <br>
                    <p>Please re log in.</p>
                    <hr>
                    
                    <div class="login-social-link centered">
                      <p>Created by weeryan17 & CodeCo</p>
                      <hr>
                    </div>
        
                </div>        
              </form>          
          
          </div>
      </div>
			<?php 
		} else if($result['status'] == "sucess"){
		?>
      <!-- ***********************************************
      MAIN CONTENT
      **************************************************** -->

	  <div id="login-page">
	  	<div class="container">
	  	
		      <form class="form-login">
		        <h2 class="form-login-heading">Dashboard</h2>
		        
		        <div class="login-social-link centered">
		            <p><strong>Discord Groups Dashboard</strong></p>
		            <a class="btn btn-theme" href="/dashboard/"><em class="fa fa-dashboard"></em> HOME</a>
		            <a class="btn btn-theme" href="link/"><em class="fa fa-cogs"></em> LINK</a>
		            <a class="btn btn-theme04"><em class="fa fa-cogs"></em> LOG OUT</a></div>
		        <hr>
		        
		        <div class="login-social-link centered">
	                <p>Welcome to the Discord Groups Dashboard!</p>
		            <br>
	                <p>Discord Groups was made by weeryan17 to link Minecraft Ranks and Discord Roles together!</p>
	                <a data-toggle="modal" href="#myModal">External Links</a>
		            <hr>
		            
		            <div class="login-social-link centered">
		              <p>Created by weeryan17 &amp; CodeCo</p>
		              <hr>
		            </div>
		
		        </div>
		
		          <!-- Modal -->
		          <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Links</h4>
		                      </div>
		                      <div class="modal-body">
		                          <a class="btn btn-theme" href="httas://twitter.com/DiscordGroups"><i class="fa fa-dashboard"></i> Twitter</a>
							  </div>
		                      <div class="modal-body">
		                          <a class="btn btn-theme" href="https://discordapp.com/oauth2/authorize?client_id=280192562583699458&scope=bot&permissions=372505664"><i class="fa fa-dashboard"></i> Discord Bot Invite</a>
							  </div>
		                      <div class="modal-body">
		                          <a class="btn btn-theme" href="https://discord.gg/GkxJhFq"><i class="fa fa-dashboard"></i> Support Discord</a>
							  </div>
		                      <div class="modal-body">
		                          <a class="btn btn-theme" href="https://github.com/LeCodeCo/Discord-Groups-Wiki/wiki/"><i class="fa fa-dashboard"></i> Wiki</a>
							  </div>
		                      <div class="modal-body">
		                          <a class="btn btn-theme" href="https://github.com/weeryan17/Wee-Utilities/tree/master/Discord%20Groups"><i class="fa fa-dashboard"></i> Source Code</a>
		
		                      </div>
		                      <div class="modal-footer">
		                          <a data-dismiss="modal" class="btn btn-theme">Close</a>
		                      </div>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		
		      </form>	  	
	  	
	  	</div>
	  </div>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>

    <!--BACKSTRETCH-->
    <!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
    <script type="text/javascript" src="assets/js/jquery.backstretch.min.js"></script>
    <script>
        $.backstretch("assets/img/login-bg.jpg", {speed: 500});
    </script>



<?php 
		} else {
			?>
		<div id="login-page">
          <div class="container">
          
              <form class="form-login">
                <h2 class="form-login-heading">Error</h2>
                <div class="login-social-link centered">
                    <p>There was an error signing you in.</p>
                    <br>
                    <p>Please try again later.</p>
                    <hr>
                    
                    <div class="login-social-link centered">
                      <p>Created by weeryan17 & CodeCo</p>
                      <hr>
                    </div>
        
                </div>        
              </form>          
          
          </div>
      </div>
			<?php 
		}
	}
?>
  </body>
</html>