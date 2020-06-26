
function getCsrfData() {
	let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");
    let data = {
    		header: header,
    		token: token
    	};
    
    return data;
}

function confirmBookmarkDelete(bookmarkId, afterDeleteAction) {
	if(confirm("Record will be deleted. Are you sure?")) {
		jQuery.ajax({
			url: '/bookmark/' + bookmarkId,
			type: 'DELETE',
			beforeSend: function( xhr ) {
				let csrf = getCsrfData();
				xhr.setRequestHeader(csrf.header, csrf.token);
			},
			success: function(result) {
				alert('Record deleted Successfully!');
				afterDeleteAction(); 
			},
			error: function(result) {
				alert('Something wrong , server Failed to delete this record!');
			}
		});
	}
}


function confirmTagDelete(tagId, afterDeleteAction) {
	if(confirm("Record will be deleted. Are you sure?")) {
		jQuery.ajax({
			url: '/tag/' + tagId,
			type: 'DELETE',
			beforeSend: function( xhr ) {
				let csrf = getCsrfData();
				xhr.setRequestHeader(csrf.header, csrf.token);
			},
			success: function(result) {
				alert('Record deleted Successfully!');
				afterDeleteAction(); 
			},
			error: function(result) {
				alert('Something wrong , server Failed to delete this record!');
			}
		});
	}
}

function pageRefresh(){
	bookmarkTable.setPage(1);// TODO: stay at current page or go to previous page if no data found in current page
}

function refreshPageTagTable(){
	tagTable.setPage(1);// TODO: stay at current page or go to previous page if no data found in current page
}

function goHome(){
	window.location.href = '/bookmark/';
}

function goTagHome(){
	window.location.href = '/tag/';
}
