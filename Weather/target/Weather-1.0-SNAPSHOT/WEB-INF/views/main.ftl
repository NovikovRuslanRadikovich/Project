<#ftl encoding='UTF-8'>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Main</title>
</head>
<body>

<#if text??>
${text}
<br>
<a href="/home">Назад</a>
<#else>
<form action="/findCity" method="post" accept-charset="utf-8">
    <label>
        <select name="city">
            <option value="Saint-Petersburg" selected="selected">Санкт-Петербург</option>
            <option value="Kazan">Казань</option>
            <option value="Samara">Самара</option>
            <option value="Nizniy Novgorod">Нижний Новгород</option>
            <option value="Vladivostok">Владивосток</option>
        </select>
    </label>
    <input type="submit" value="Погода">
</form>
</#if>
</body>

</html>