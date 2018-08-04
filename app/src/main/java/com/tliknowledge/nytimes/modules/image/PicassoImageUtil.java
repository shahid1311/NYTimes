package com.tliknowledge.nytimes.modules.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tliknowledge.nytimes.modules.logger.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class PicassoImageUtil {

    private Context mContext;
    private Logger logger = new Logger(PicassoImageUtil.class);

    public PicassoImageUtil(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * Set the image to download without setting the default image
     *
     * @param imageUrl  - String containing image url
     * @param imageView - ImageView on which the downloaded image will be set
     */
    public void setImageWithoutDefault(String imageUrl, ImageView imageView) {
        if (mContext != null) {
            Picasso
                    .with(mContext)
                    .load(imageUrl)
                    .into(imageView);
        }
    }

    /**
     * Set the image to download with default image and error image
     *
     * @param imageUrl       - String containing image url
     * @param imageView      - ImageView on which the downloaded image will be set
     * @param defaultImageId - resource id of the image that needs to be displayed as default image
     * @param errorImageId   - resource id of the image that needs to be displayed if loading the image fails
     */
    public void setImageWithDefaultAndError(String imageUrl, ImageView imageView, int defaultImageId, int errorImageId) {
        if (mContext != null) {
            Picasso
                    .with(mContext)
                    .load(imageUrl)
                    .placeholder(defaultImageId)
                    .error(errorImageId)
                    .into(imageView);
        }
    }

    /**
     * Set the image to download with default image and error image
     *
     * @param imageUrl       - String containing image url
     * @param imageView      - ImageView on which the downloaded image will be set
     * @param defaultImageId - resource id of the image that needs to be displayed as default image. This image will be set as the error image as well
     */
    public void setImageWithDefault(String imageUrl, ImageView imageView, int defaultImageId) {
        setImageWithDefaultAndError(imageUrl, imageView, defaultImageId, defaultImageId);
    }

    /**
     * Set the image to download with default image and error image
     *
     * @param imageUri  - Uri of the image from your device
     * @param imageView - ImageView on which the downloaded image will be set
     */
    public void setImageFromUri(Uri imageUri, ImageView imageView) {
        if (mContext != null) {
            Picasso
                    .with(mContext)
                    .load(imageUri)
                    .into(imageView);
        }
    }

    public void setImageFromUriWithDefault(Uri imageUri, ImageView imageView, int placeHolder) {
        if (mContext != null) {
            Picasso
                    .with(mContext)
                    .load(imageUri)
                    .placeholder(placeHolder)
                    .into(imageView);
        }
    }

    /**
     * Set the image to download with default image and error image
     *
     * @param imageFile - File containing the image
     * @param imageView - ImageView on which the downloaded image will be set
     */
    public void setImageFromFile(File imageFile, ImageView imageView) {
        if (mContext != null) {
            Picasso
                    .with(mContext)
                    .load(imageFile)
                    .into(imageView);
        }
    }

    /**
     * Set the image to download and in a circular shape with default image and error image
     *
     * @param imageUrl
     * @param imageView
     * @param defaultImageId
     */
    public void setImageByLinkAndTransformToCircle(String imageUrl, ImageView imageView, int defaultImageId) {
        if (mContext != null) {
            Picasso
                    .with(mContext)
                    .load(imageUrl)
                    .transform(new CircularImageTransformation())
                    .placeholder(defaultImageId)
                    .error(defaultImageId)
                    .into(imageView);
        }
    }


    /**
     * Set the image to download and in a circular shape with default image and error image
     *
     * @param imageUri
     * @param imageView
     * @param defaultImageId
     */
    public void setImageByUriAndTransformToCircle(Uri imageUri, ImageView imageView, int defaultImageId) {
        if (mContext != null) {
            Picasso
                    .with(mContext)
                    .load(imageUri)
                    .transform(new CircularImageTransformation())
                    .placeholder(defaultImageId)
                    .error(defaultImageId)
                    .into(imageView);
        }
    }

    /**
     * Set the image to download and in a circular shape with default image and error image
     *
     * @param file
     * @param imageView
     * @param defaultImageId
     */
    public void setImageByFileAndTransformToCircle(File file, ImageView imageView, int defaultImageId) {
        if (mContext != null) {
            Picasso
                    .with(mContext)
                    .load(file)
                    .transform(new CircularImageTransformation())
                    .placeholder(defaultImageId)
                    .error(defaultImageId)
                    .into(imageView);
        }
    }

    public void setImageFromResource(int resID, ImageView imageView) {
        if (mContext != null) {
            Picasso
                    .with(mContext)
                    .load(resID)
                    .into(imageView);
        }
    }

    public void setImageFromResourceAndTransformToCircle(int resID, ImageView imageView) {
        if (mContext != null) {
            Picasso
                    .with(mContext)
                    .load(resID)
                    .transform(new CircularImageTransformation())
                    .into(imageView);
        }
    }

    /**
     * Returns an Uri of the bitmap image
     *
     * @param inContext
     * @param inImage
     * @return
     */
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    /**
     * Returns a compressed bitmap as per decided width and height
     *
     * @param capturedImage
     * @param width
     * @param height
     * @return
     */
    public Bitmap compressImage(Bitmap capturedImage, int width, int height) {
        return Bitmap.createScaledBitmap(capturedImage, width, height, true);
    }

    /**
     * Returns a bitmap from Uri
     *
     * @param context
     * @param uri
     * @return
     * @throws IOException
     */
    public Bitmap getBitmapFromURI(Context context, Uri uri) throws IOException {
        return MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
    }

    public void invalidateCacheByUri(Uri outputFileUri) {
        if (mContext != null) {
            Picasso
                    .with(mContext)
                    .invalidate(outputFileUri);
        }
    }

    /**
     * Return Bitmap from downloaded image
     *
     * @param imageUrl
     * @return
     */
    public Bitmap getBitMapFromUrl(String imageUrl) {
        Bitmap bitmap = null;
        if (mContext != null) {
            try {
                bitmap = Picasso.with(mContext).load(imageUrl).get();
            } catch (Exception e) {
                logger.error(e);
            }
        }
        return bitmap;
    }
}
