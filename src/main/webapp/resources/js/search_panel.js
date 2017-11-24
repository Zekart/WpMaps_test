function openSearch(){
    if($("#search_panel").is(":visible")){
        $('#search_panel').hide();
    }else{
        $('#search_panel').show();
    }
}   

function geocode() {    
    
    PF('map').geocode("Николаев," + document.getElementById('address_search_input').value);
    
}
