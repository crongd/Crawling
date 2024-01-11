(function (){
    const items = document.querySelectorAll('Dropdown-Select__Dropdown__Item');
    let result = "";
    for (let i = 0; i < items.length; i++) {
        result += items[i].textContent.trim();
        if(i !== items.length - 1){
            result += ',';
        }
    }
    return result;
}());

(function (){
    const items = document.querySelectorAll('.Dropdown-Select__Dropdown__Item');
    let result = "";
    for (let i = 0; i < items.length; i++) {
        result += items[i].textContent.trim();
        if(i !== items.length - 1){
            result += ',';
        }
    }
    console.log(result);
    return result;
}());
