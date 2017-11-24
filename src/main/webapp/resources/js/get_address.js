    function ClearInputs(){
        document.getElementById('j_idt189:ff:wizard_form:h_number').value = "";
        document.getElementById('j_idt189:ff:wizard_form:h_street').value = "";        
    }
    
    function GetAddress() {       
        var geocoder = geocoder = new google.maps.Geocoder();

        var lat = parseFloat(document.getElementById("lat").value);
        var lng = parseFloat(document.getElementById("lng").value);
        var point = new google.maps.LatLng(lat, lng);

        var bounds = new google.maps.LatLngBounds();
        bounds.extend(point);

        var street = "Unknow";
        var number ="Unknow";
        
        var n = document.getElementById('j_idt189:ff:wizard_form:h_number');
        var s = document.getElementById('j_idt189:ff:wizard_form:h_street');

        geocoder.geocode({ 'location': point }, function (results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
                if (results[0]) {
                    for (var item in results[0].address_components) {
                        for (var n_item in results[0].address_components[item].types) {
                            if (results[0].address_components[item].types[n_item] === "street_number") {
                                street_number = results[0].address_components[item];                                    
                            }
                            if (results[0].address_components[item].types[n_item] === "route") {
                                street_name = results[0].address_components[item];                                    
                            }
                        }
                    }
                    number = street_number.short_name;
                    street = street_name.short_name;

                    n.value = number;
                    s.value = street.substr(street.indexOf(' ')+1);
                    //strt.onchange();
                }
            }
        });
    } 
