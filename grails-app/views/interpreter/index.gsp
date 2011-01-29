<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
      <meta name="layout" content="main"></meta>
  </head>
  <body>
    <div><g:message code="prompt"/>: <input id="number" name="number" maxlength="9"/></div>
    <div id="text" style="height: 2em"></div>
    <g:javascript>

        var interpret = function() {
            $('#text').text('');

            var number = $('#number').val();
            if (number) {
                $.ajax({
                    url: "${createLink(action: 'interpret')}/" + number,
                    dataType: 'html',
                    success: function(text) {
                        $('#text').addClass('message');                        
                        $('#text').removeClass('errors');
                        $('#text').text(text);
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrow) {
                        $('#text').removeClass('message');                        
                        $('#text').addClass('errors');
                        $('#text').text(XMLHttpRequest.responseText);
                    }
                });
            } else {
                $('#text').removeClass('message');
                $('#text').removeClass('errors');
            }
        }

        $("#number").keyup(function() {
            $(this).stopTime('interpret');
            $(this).oneTime(300, "interpret", interpret);
        });
    </g:javascript>
  </body>
</html>