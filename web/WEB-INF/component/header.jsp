<nav class="navbar navbar-dark bg-dark justify-content-center">

    <a class="navbar-brand mr-5 font-weight-bold font-italic" href="/home">Harry Potter Application</a>
    <div class="text-center">
        <div class="btn-group" role="group">
            <form action="/show-characters" method="post">
                <button class="btn btn-light btn-lg mr-5" type="submit">Characters</button>
            </form>
            <form action="/show-locations" method="post">
                <button class="btn btn-light btn-lg mr-5" type="submit">Locations</button>
            </form>
            <form action="/show-pets" method="post">
                <button class="btn btn-light btn-lg mr-5" type="submit">Pets</button>
            </form>

            <div class="dropdown">
                <button type="button" class="btn btn-light btn-lg mr-5 dropdown-toggle" data-toggle="dropdown">Items</button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="/view-item?itemType=WANDS">Wands</a>
                    <a class="dropdown-item" href="/view-item?itemType=WEAPONS">Weapon</a>
                    <a class="dropdown-item" href="/view-item?itemType=BROOMSTICKS">Broomstick</a>
                    <a class="dropdown-item" href="/view-item?itemType=ROBES">Robes</a>


                    <c:forEach items="${itemTypes}" var="type">
                    <a class="dropdown-item" href="/view-item?itemType=${type}">${type}</a>
                    </c:forEach>


                </div>
            </div>

        </div>
            <img src="/image/logo.png" width="60" height="60"/>
        </div>
    </div>

</nav>
