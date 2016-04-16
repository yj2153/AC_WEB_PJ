<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/hanbitsw/acDeal/jqgrid/css/ui.jqgrid.css"/>" />
<script
	src="<c:url value="/hanbitsw/acDeal/jqgrid/js/jquery.jqGrid.min.js"/>"></script>
<script
	src="<c:url value="/hanbitsw/acDeal/jqgrid/src/i18n/grid.locale-kr.js"/>"></script>

    <meta charset="utf-8" />
    <title>jqGrid Loading Data - Toolbar Searching</title>
</head>
<body>
<div style="margin-left:20px;">
   <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>
    <script type="text/javascript">

        $(document).ready(function () {
            $("#jqGrid").jqGrid({
                url: 'data.json',
                editurl: 'clientArray',
                datatype: "json",
                colNames: ["Customer ID", '구분', '카테고리', '상세내용', '금액', '거래일'],
                colModel: [
                    {
						label: 'CustomerID',
                        name: 'CustomerID',
                        width: 75
                    },
                    {
						label : 'Company Name',
                        name: 'CompanyName',
                        width: 140,
                        editable: true,
                        // must set editable to true if you want to make the field editable
                        // set options related to the layout of the Edit and Add Forms
                        formoptions: {
                            colpos: 1, // the position of the column
                            rowpos: 1, // the position of the row
                            label: "Company Name: " // the label to show for each input control                    
                            //elmsuffix: " * " // the suffix to show after that
                        }
                    },
                    {
						label : 'Phone',
                        name: 'Phone',
                        width: 100,
                        editable: true,
                        formoptions: {
                            colpos: 1,
                            rowpos: 2
                        }
                    },
                    {
						label: 'Postal Code',
                        name: 'PostalCode',
                        width: 80,
                        editable: true,
                        formoptions: {
                            colpos: 2,
                            rowpos: 1,
                            label: "Postal Code"
                        }
                    },
                    {
						label: 'City',
                        name: 'City',
                        width: 140,
                        editable: true,
                        formoptions: {
                            colpos: 2,
                            rowpos: 2
                        }
                    }
                ],
				loadOnce : true,
				viewrecords: true,
                width: 780,
                height: 200,
                rowNum: 10,
                pager: "#jqGridPager"
            });

            $('#jqGrid').navGrid('#jqGridPager',
                // the buttons to appear on the toolbar of the grid
                { edit: true, add: true, del: true, search: false, refresh: false, view: false, position: "left", cloneToTop: false },
                // options for the Edit Dialog
                {
                    height: 'auto',
                    width: 620,
                    editCaption: "The Edit Dialog",
                    recreateForm: true,
                    closeAfterEdit: true,
                    errorTextFormat: function (data) {
                        return 'Error: ' + data.responseText
                    }
                },
                // options for the Add Dialog
                {
                    height: 'auto',
                    width: 620,
                    closeAfterAdd: true,
                    recreateForm: true,
                    errorTextFormat: function (data) {
                        return 'Error: ' + data.responseText
                    }
                },
                // options for the Delete Dailog
                {
                    errorTextFormat: function (data) {
                        return 'Error: ' + data.responseText
                    }
                });
        });

    </script>

</body>
</html>