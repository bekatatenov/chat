<#import "partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/messages.js"] styles=[]>

    <div class="container" style="padding: 10px">
        <div class="list-group justify-content-center w-50 mx-auto" id="chatHeader">
            <p id="${chatId}" class="list-group-item list-group-item-action list-group-item-dark">
                List Of Messages
            </p>

            <div class="list-group justify-content-center w-50 mx-auto">
                <div id="idList">
                    <div id="0"></div>
                </div>
            </div>


            <div id="messages">
                <div id="0"></div>
            </div>


            <div class="modal-body">
                <form id="form-message">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                               value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div class="form-group">
                        <input type="text" name="text" class="form-control" aria-describedby="text" autofocus>
                    </div>
                    <button type="button" class="send btn btn-secondary" placeholder="Write Message" name="email"
                            onclick="sendMessage(${chatId})">
                        SendMessage
                    </button>
                </form>
            </div>

        </div>
    </div>

</@main.renderWith>