<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>HH</title>
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>

<div class="container">

    <#if user?has_content>
        ${user.name}
     <#else>
        Pizdec
    </#if>

</div>
</body>
</html>