<#import "partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/main.js"] styles=[]>

    <div class="container" style="padding: 10px">
        <div class="list-group justify-content-center w-50 mx-auto">
            <a href="#" class="list-group-item list-group-item-action list-group-item-dark">
                List Of Chats
            </a>
            <#list chats as chat>
                <a href="/chat/${chat.id}" class="list-group-item list-group-item-action">ID:${chat.id}
                      ||  Name:${chat.nameOfChat}</a>
            </#list>
        </div>

        <div class="justify-content-center w-50 mx-auto" style="padding: 10px">
            <#list 1..pages as x>
                <a class="pageButton" style="color: black; text-align: center" href="?page=${x}">${x}</a>
            </#list>
        </div>
    </div>
</@main.renderWith>