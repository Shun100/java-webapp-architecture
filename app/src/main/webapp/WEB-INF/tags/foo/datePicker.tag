<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="dateFormat" required="true" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- jQuery UI CSS -->
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<!-- jQuery UI JS -->
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"></script>

<script>
  jQuery(function($){
    $("#${id}").datepicker({ dateFormat: "${dateFormat}" });
  });
</script>

<input type="text" id="${id}" />
