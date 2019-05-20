# Đề tài Hadoop


1.Cấu trúc file
- input: là folder chứa tập ảnh cần lọc.
- workspace: là folder chứa source code.
- Allclass.java: là file được compile từ source code trong folder workspace, gồm có các class:
	+ CollectPathFile : tạo file chứa đường dẫn đến các ảnh trong folder input
	+ ImageFileToHadoopSequenceFile: chuyển đổi các ảnh sang file sequence
	+ ImgDriver: đây là file chính của hadoop tổng hợp các quá trình MapReduce
	+ ImgDupMapper: class Map
	+ ImgDupReducer: class Reduce
	+ Skein512: class giải thuật Skein
- pathinput.txt: là file lưu đường dẫn đến các ảnh trong folder input được tạo ra bởi class CollectPathFile
- output.txt: là file output chứa các đường dẫn và mã hash của các ảnh được chọn lọc không trùng nhau.
- run.sh: là file chứa các câu lệnh để chạy chương trình so sánh ảnh trùng nhau trên hadoop.


2.Set up để chạy 
```bash
./run.sh
```




