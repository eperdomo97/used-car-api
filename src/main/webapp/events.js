// AJAX: Asynchronous Javascript and XML
// DOMContentLoaded: fires when the document object model is fully loaded
document.addEventListener("DOMContentLoaded", function(){
    // create a GET request to retrieve all cars and add them to the table

    // 1. make an xhr object
    let xhr = new XMLHttpRequest();	// make HTTP requests

    // 2. define what happens during the AJAX call
    xhr.onreadystatechange = function() {
        // Ready states
        // 0 - unsent
        // 1 - opened
        // 2 - headers received 
        // 3 - loading
        // 4 - done --> the one we care about

        if(xhr.readyState === 4) {
            // ONLY do something once the request is completed
            console.log(JSON.parse(xhr.responseText));	//JSON.parse() to parse JSON
            
            var carArray = JSON.parse(xhr.responseText);

            // looping through the array and adding each element to our table
            carArray.forEach(carElement => {
                addCarToTable(carElement);
            });
        }
    }

    // 3. open the xhr call with the http request verb and the url
    xhr.open('GET', '/cars-api/api/cars')

    // 4. send the ajax call
    xhr.send();
});

var currentRow;
var modal = document.getElementById('editModal');



// submit a new listing when submit button is pressed
document.getElementById('new-car-form').addEventListener('submit', function(event) {
    event.preventDefault();		//prevent default form actions from occuring

    //get the data from the form
    var makeOnForm = document.getElementById('sub-make').value;	
    var modelOnForm = document.getElementById('sub-model').value;
    var yearOnForm = document.getElementById('sub-year').value;
    var colorOnForm = document.getElementById('sub-color').value;
    var mileageOnForm = document.getElementById('sub-mileage').value;
    var priceOnForm = document.getElementById('sub-price').value;
    var titleOnForm = document.getElementById('sub-title').value;

    var car = {
        make : makeOnForm,
        model : modelOnForm,
        modelYear : yearOnForm,
        color : colorOnForm,
        mileage : mileageOnForm,
        price : priceOnForm,
        title : titleOnForm
    };

    // make AJAX call
    
    // 1. make an xhr object
    let xhr = new XMLHttpRequest();	// make HTTP requests

    // 2. define what happens during the AJAX call
    xhr.onreadystatechange = function() {

        if(xhr.readyState === 4) {

            // getting back the updated car object
            var updatedCar = JSON.parse(xhr.responseText);

            // adding the updated car to our table
            addCarToTable(updatedCar);

            // reset the form
            // document.getElementById('new-car-form').reset();
        }
    }

    // 3. open the xhr call with the http request verb and the url
    xhr.open('POST', '/cars-api/api/cars')

    // 4. send the ajax call
    // converting from variable to JSON and sending it in the POST request
    xhr.send(JSON.stringify(car)); 
});

// edit listing when save button is pressed
document.getElementById('edit-car-form').addEventListener('submit', function(event) {
    event.preventDefault();		//prevent default form actions from occuring

    //get the data from the form
    var makeOnForm = document.getElementById('edit-make').value;	
    var modelOnForm = document.getElementById('edit-model').value;
    var yearOnForm = document.getElementById('edit-year').value;
    var colorOnForm = document.getElementById('edit-color').value;
    var mileageOnForm = document.getElementById('edit-mileage').value;
    var priceOnForm = document.getElementById('edit-price').value;
    var titleOnForm = document.getElementById('edit-title').value;

    var car = {
		id : currentRow,
        make : makeOnForm,
        model : modelOnForm,
        modelYear : yearOnForm,
        color : colorOnForm,
        mileage : mileageOnForm,
        price : priceOnForm,
        title : titleOnForm
    };

    // make AJAX call
    
    // 1. make an xhr object
    let xhr = new XMLHttpRequest();	// make HTTP requests

    // 2. define what happens during the AJAX call
    xhr.onreadystatechange = function() {

        if(xhr.readyState === 4) {

            // getting back the updated car object
            var updatedCar = JSON.parse(xhr.responseText);

            // updating the row in our table
            updateListing(updatedCar);
        }
    }

    // 3. open the xhr call with the http request verb and the url
    xhr.open('PUT', '/cars-api/api/cars')

    // 4. send the ajax call
    // converting from variable to JSON and sending it in the PUT request
    xhr.send(JSON.stringify(car)); 
});

// delete listing when delete button is pressed
document.getElementById('edit-car-form').addEventListener('reset', function(event) {
    event.preventDefault();		//prevent default form actions from occuring

    //get the data from the form
    var makeOnForm = document.getElementById('edit-make').value;
    var modelOnForm = document.getElementById('edit-model').value;
    var yearOnForm = document.getElementById('edit-year').value;
    var colorOnForm = document.getElementById('edit-color').value;
    var mileageOnForm = document.getElementById('edit-mileage').value;
    var priceOnForm = document.getElementById('edit-price').value;
    var titleOnForm = document.getElementById('edit-title').value;

    var car = {
		id : currentRow,
        make : makeOnForm,
        model : modelOnForm,
        modelYear : yearOnForm,
        color : colorOnForm,
        mileage : mileageOnForm,
        price : priceOnForm,
        title : titleOnForm
    };

    // make AJAX call
    
    // 1. make an xhr object
    let xhr = new XMLHttpRequest();	// make HTTP requests

    // 2. define what happens during the AJAX call
    xhr.onreadystatechange = function() {

        if(xhr.readyState === 4) {

            // deleting the row in our table
            deleteListing(car);
        }
    }

    // 3. open the xhr call with the http request verb and the url
    xhr.open('DELETE', '/cars-api/api/cars')

    // 4. send the ajax call
    // converting from variable to JSON and sending it in the DELETE request
    xhr.send(JSON.stringify(car)); 
});


// low level DOM manipulation

//add a car to table (duh)
function addCarToTable(car) {

    // creating all of our needed DOM elements
    var tr = document.createElement('tr');	// creates our <tr> element
    var id = document.createElement('td');	// creates a <td> for our id
    var make = document.createElement('td');	
    var model = document.createElement('td');
    var year = document.createElement('td');
    var color = document.createElement('td');
    var mileage = document.createElement('td');
    var price = document.createElement('td');
    var title = document.createElement('td');    

    // adding data to the elements
    tr.id = car.id;
    
    //row properties
    
    //allows user to click on row to open modal for editing row
    tr.setAttribute('data-bs-toggle', 'modal');
    tr.setAttribute('data-bs-target', '#editModal');
    
    //populate the modal input boxes upon clicking table row, store current row id
    tr.addEventListener('click', function(event){
		event.preventDefault();
		currentRow = tr.id;
        document.getElementById('edit-make').setAttribute('value', car.make);
        document.getElementById('edit-make').value = car.make;
        document.getElementById('edit-model').setAttribute('value', car.model);
        document.getElementById('edit-model').value = car.model;
        document.getElementById('edit-year').setAttribute('value', car.modelYear);
        document.getElementById('edit-year').value = car.modelYear;
        document.getElementById('edit-color').setAttribute('value', car.color);
        document.getElementById('edit-color').value = car.color;
        document.getElementById('edit-mileage').setAttribute('value', car.mileage);
        document.getElementById('edit-mileage').value = car.mileage;
        document.getElementById('edit-price').setAttribute('value', car.price);
        document.getElementById('edit-price').value = car.price;
        document.getElementById('edit-title').setAttribute('value', car.title);
        document.getElementById('edit-title').value = car.title;
	});
	
	//populate table data
    id.innerText = car.id;
    make.innerText = car.make;
    model.innerText = car.model;
    year.innerText = car.modelYear;
    color.innerText = car.color;
    mileage.innerText = car.mileage;
    price.innerText = "$" + car.price;
    title.innerText = car.title;

    //add <td>'s to our <tr>
    tr.appendChild(id);
    tr.appendChild(make);
    tr.appendChild(model);
    tr.appendChild(year);
    tr.appendChild(color);
    tr.appendChild(mileage);
    tr.appendChild(price);
    tr.appendChild(title);    

    //add our <tr> to our <tbody>
    document.getElementById('car-table-body').appendChild(tr);
    
}

// updates a row on the table
function updateListing(car){
	row = document.getElementById(car.id);
	row.cells[1].innerHTML = car.make;
	row.cells[2].innerHTML = car.model;
	row.cells[3].innerHTML = car.modelYear;
	row.cells[4].innerHTML = car.color;
	row.cells[5].innerHTML = car.mileage;
	row.cells[6].innerHTML = "$" + car.price;
	row.cells[7].innerHTML = car.title;
	
}

// delete a row from the table
function deleteListing(car){
	row = document.getElementById(car.id);
	row.parentNode.removeChild(row);
}