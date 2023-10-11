function isStringConvertibleToInt(inputString) {
    // Use parseInt to attempt the conversion
    const parsedInt = parseInt(inputString, 10); // The second argument specifies the radix (base 10)

    // Check if the result is a number (not NaN) and the string represents a valid integer
    return !isNaN(parsedInt) && String(parsedInt) === inputString;
}

// On subsequent pages, read the cookie to access the user's email
function getUserEmail() {
    const cookies = document.cookie.split('; ');
    for (const cookie of cookies) {
        const [name, value] = cookie.split('=');
        if (name === 'userEmail') {
            return value;
        }
    }
    return null;
}


function getProductId() {
    const cookies = document.cookie.split('; ');
    for (const cookie of cookies) {
        const [name, value] = cookie.split('=');
        if (name === 'productId') {
            return value;
        }
    }
    return null;
}



function getSellerId() {
    const cookies = document.cookie.split('; ');
    for (const cookie of cookies) {
        const [name, value] = cookie.split('=');
        if (name === 'sellerId') {
            return value;
        }
    }
    return null;
}


function handleHome(data) {
    // Check if the data contains the necessary information to determine where to redirect
    console.log("inside handle home")
    if (data!=null) {
        console.log(data.user);
        // localStorage.setItem('userEmail', data.user);
        // After successful login, set a cookie
        document.cookie = `userEmail=${data.user}; path=/;`;
        window.location.href = 'Home.html';
    } else {
        console.error('Error: Invalid data for redirection');
    }
}

function getName(){
    const emailId=getUserEmail();
    console.log(emailId);
    if(emailId===null){
        window.location='index.html';
        return;
    }
    const url = `/home/${emailId}`;
    const formData = {
        email: emailId
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => response.json())
        .then(data => {
            if (data.status === 'error') {
                // Display an alert with the error message
                alert(data.message);
                console.log(data);
            } else{
                document.getElementById('username').textContent="Welcome to the Market Place, "+data.name;
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function logout(){
    console.log(getUserEmail());
    const formData = {
        email: getUserEmail()
    };

    fetch('/logout', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    });
}


function sellProduct(productName, productDescription,productPrice,sellerAddress){
    if(!isStringConvertibleToInt(productPrice)){
        alert("Enter valid price amount");

        return;
    }

    const productData = {
        sellerId:getUserEmail(),
        name: productName,
        description: productDescription,
        price: productPrice,
        address: sellerAddress
    };
    fetch('/sell', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(productData)})
            .then(response => response.json())
            .then(data => {
                if (data.status === 'error') {
                    // Display an alert with the error message
                    alert(data.message);
                    console.log(data);
                } else{
                    //show popup product added on market place
                    alert(data.message);
                    location.reload();
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });

}


function createBidModeal(){
    return `
    <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="bidModalLabel">Place a Counter offer</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <!-- Add your bid form here -->
                    <form>
                        <div class="form-group">
                            <label for="bidAmount">Counter Amount</label>
                            <input type="text" class="form-control" id="bidAmount" placeholder="Enter your offer amount">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="place-bid-button">Send offer</button>
                </div>
            </div>
        </div>
    `;
}



function createProductCard(product) {
    return `
        <div class="col-md-4 mb-4">
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title">${product.name}</h3>
                    <h4 class="card-title">Price: ${product.price}</h4>
                    <h5>Address: ${product.address}</h5>
                    <p class="card-text">Description: ${product.description}</p>
                    <button class="btn btn-primary btn-block" data-toggle="modal" data-target="#bidModal" onclick=saveProductDetails(${JSON.stringify(product)})>Buy</button>
                </div>
            </div>
        </div>
    `;
}



function saveProductDetails(product){
    console.log("trying to save product");
    console.log(product.sellerId);
    console.log(product.productId)
    console.log(product.id);
    // localStorage.setItem('productId', product.id);
    // localStorage.setItem('sellerId', product.sellerId);

    document.cookie = `productId=${product.id}; path=/;`;
    document.cookie = `sellerId=${product.sellerId}; path=/;`;
}
function getListOfProducts(){

    fetch('/market', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
        })
        .then(response => response.json())
        .then(data => {
            if (Array.isArray(data)) {
                //add products to homePage
                const productsContainer = document.querySelector('.row'); // Select the container

                // Loop through the products and add them to the container
                data.forEach(product => {
                    const productCard = createProductCard(product);
                    productsContainer.innerHTML += productCard;
                });
            }
            else {
                console.error('Invalid data format: Expected an array.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function placeBid(bidAmount){
    if(!isStringConvertibleToInt(bidAmount)){
        alert("Enter valid bid amount");

        return;
    }

    const bidData = {
        buyerId: getUserEmail(),
        offer: bidAmount,
        sellerId: getSellerId(),
        productId: getProductId()
    };
    console.log(bidData);
    fetch('/bid', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bidData)})
        .catch(error => {
            console.error('Error:', error);
        });
}

function createOfferRecievedCard(name, price,offerId,productId){
    return `
        <li class="list-group-item">
            <div class="d-flex justify-content-between">
                <div>
                    <h5>${name}</h5>
                    <p>Price: ${price}</p>
                </div>
                <div>
                    <button type="button" class="btn btn-success mr-2" onclick=acceptOffer(${JSON.stringify(productId)},${JSON.stringify(offerId)})>Accept</button>
                    <button type="button" class="btn btn-danger" onclick=rejectOffer(${JSON.stringify(offerId)})>Deny</button>
                </div>
            </div>
        </li>
    `;
}

function acceptOffer(productId,offerId){
    console.log(productId);
    const url = `/acceptOffer/${productId}/${offerId}`;

    fetch(url,{
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
    }).catch(error => {
        console.error('Error:', error);
    });
    location.reload();
}
function rejectOffer(offerId){
    console.log(offerId,);
    const url = `/denyOffer/${offerId}`;

    fetch(url,{
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
    }).catch(error => {
        console.error('Error:', error);
    });
    location.reload();
}
function getOffers(){
    const emailId=getUserEmail();
    const url = `/offerReceived/${emailId}`;
    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => response.json())
        .then(data => {
            if (Array.isArray(data)) {
                //add products to homePage
                const productsContainer = document.querySelector('.listOffer'); // Select the container
                // Loop through the products and add them to the container\
                data.forEach(product => {
                    console.log(product.productId);

                    const url=`/getProduct/${product.productId}`;
                    fetch(url, {
                        method: 'GET',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                    }).then(response => response.json())
                        .then(data => {
                            const productCard = createOfferRecievedCard(data.name, product.offer, product.id,product.productId);
                            productsContainer.innerHTML += productCard;
                        })
                        .catch(error => {
                            console.error('Error:', error);
                        });
                });
            }
            else {
                console.error('Invalid data format: Expected an array.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}


function createPurchaseCard(product) {
    return `
        <div class="col-md-10 mb-10">
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title">${product.name}</h3>
                    <p class="card-text">Description: ${product.description}</p>
                </div>
            </div>
        </div>
    `;
}

function getPurchase(){
    const emailId=getUserEmail();
    const url=`/purchase/${emailId}`;

    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => response.json())
        .then(data => {
            if (Array.isArray(data)) {
                //add products to homePage
                const productsContainer = document.querySelector('.purchaseList'); // Select the container

                // Loop through the products and add them to the container
                data.forEach(product => {
                    const productCard = createPurchaseCard(product);
                    productsContainer.innerHTML += productCard;
                });
            }
            else {
                console.error('Invalid data format: Expected an array.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}
