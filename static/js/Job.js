$(document).ready(function(){
  $("#Index").submit(function() { return false; });
  $("#Index #Submit").click(function(){
	  getAllJobs();
  });
});

function getAllJobs()
{
  $.getJSON(
    "http://localhost:8080/job/",
    function( data ) {
      var items = [];
      $.each( data, function( key, val ) {
        items.push( "<li class=\"media\"><a class=\"media-left\" href=\"/job.html?identifier=" + val.id + "\"><span class=\"glyphicon glyphicon-tag\" aria-hidden=\"true\"></a><div class=\"media-body\"></span><h4 class=\"media-heading\">ID: " + val.id + "</h4><p>Name: " + val.name + "</p></div></li>" );
      });
      $( "#JobIndex" ).replaceWith($( "<ul/>", {
        "class": "media-list",
        "id": "ScheduledJobs",
        html: items.join( "" )
      }));
    });
}