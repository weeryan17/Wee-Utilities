/**
 * 
 */
var $document = $(document);

var time = 0;

$('.hours').html('<p>' + time);
$('.hours').html('<p>' + time + ":00");
$('.go').html('<a href="?dir=' + dir + '&file=0' + (time) + '.00.log">GO!</a>');

function up(dir){
	if(!(time + 1 >= 25)){
		time++;
		$('.hours').html('<p>' + time + ":00");
		if(time <= 9){
			$('.go').html('<a href="?dir=' + dir + '&file=0' + (time) + '.00.log">GO!</a>');
		} else {
			$('.go').html('<a href="?dir=' + dir + '&file=' + (time) + '.00.log">GO!</a>');
		}
	}
}

function down(dir){
	if(!(time - 1 <= -1)){
		time--;
		$('.hours').html('<p>' + time + ":00");
		if(time <= 9){
			$('.go').html('<a href="?dir=' + dir + '&file=0' + (time) + '.00.log">GO!</a>');
		} else {
			$('.go').html('<a href="?dir=' + dir + '&file=' + (time) + '.00.log">GO!</a>');
		}
	}
}