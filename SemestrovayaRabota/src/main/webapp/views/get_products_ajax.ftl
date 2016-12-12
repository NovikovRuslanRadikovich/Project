<#if productsDecade??>

    <script>

    </script>

    <#list productsDecade as product>

    <div class="news_item">
        <div class="news_item_text_time">Имя: ${product.getName()}</div>
        <div class="news_item_text">Описание: ${product.getDescription()}</div>
        <div class="news_item_text">Цена: ${product.getPrice()}</div>
        <div class="news_item_text">Оценка: ${product.getQuantity()}</div>
        <br/><br/>
        <img style="margin: auto" src="/imagesOfproducts/${product.getName()}.jpg" class="images"/>
        <br/><br/>
        <a href="/product_detail/${product.getId()}">Подробно...</a>
        <#if admin??>
        <p> <button style="display: block" onclick="deleteProduct(${product.getId()})">Удалить</button> </p>

        </#if>
        <#if admin??>
            <p> <a href="/edit_product/${product.getId()}">Редактировать</a> </p>
        </#if>
    </div>

        <#if product_index < productsDecade?size - 1>
        <div class="divider"></div>
        </#if>
    </#list>

</#if>

<#if nextProducts??>

     <#list nextProducts as nextProduct>

         <a href="../product_detail/${nextProduct.getId()}"> ${nextProduct_index + 1}  </a> &nbsp;

     </#list>

</#if>