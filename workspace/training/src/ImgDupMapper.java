import java.io.IOException;

import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.BytesWritable;

import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.Mapper;

import org.apache.hadoop.mapreduce.Reducer;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;

import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import org.apache.hadoop.util.GenericOptionsParser;

public class ImgDupMapper extends Mapper<Text, BytesWritable, Text, Text> {

	public void map(Text key, BytesWritable value, Context context) throws IOException, InterruptedException {
		String skein;
		try {

			skein = calculateSkein(value.getBytes());

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

			context.setStatus("Internal error - can't find the algorithm for calculating the md5");

			return;

		}

		Text md5txt = new Text(skein);
		context.write(md5txt, key);
	}

	static String calculateSkein(byte[] imageData) throws NoSuchAlgorithmException {

		byte[] hashCode = new byte[512];
		Skein512.hash(imageData, hashCode);
		
		String hexStr = new String();

		for (int i=0; i < hashCode.length; i++) {		 
			hexStr += Integer.toString( ( hashCode[i] & 0xff ) + 0x100, 16).substring( 1 );
		}
		return hexStr;	 
	}

}