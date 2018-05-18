package ro.punctart.artloading;

/**
 * Created by Costin on 30.05.2017.
 */

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

/**
 * Created by dg on 5/29/2017.
 */

public class CriticiCustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] critici;
    private final String[] nume;
    ExpandableTextView expandableTextView;


    public CriticiCustomList(Activity context, String[] nume, String[] critici){
        super(context, R.layout.list_single_critici, critici);
        this.context = context;
        this.critici = critici;
        this.nume = nume;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        Log.d("raici","am ajuns|");

        View rowView = inflater.inflate(R.layout.list_single_critici, null, true);
        TextView numeTV = (TextView) rowView.findViewById(R.id.nume_critic);
        TextView criticaTV = (TextView) rowView.findViewById(R.id.text_critica);
       // criticaTV.setText(critici[position]);
        numeTV.setText(nume[position] + ":");

        //makeTextViewResizable(criticaTV, 3, "View More", true);

        //cod nou
        expandableTextView  = (ExpandableTextView) rowView.findViewById(R.id.expandable_text_view);
        expandableTextView.setText(critici[position]);

        return rowView;
    }

//    public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {
//
//        if (tv.getTag() == null) {
//            tv.setTag(tv.getText());
//        }
//        ViewTreeObserver vto = tv.getViewTreeObserver();
//        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//
//            @SuppressWarnings("deprecation")
//            @Override
//            public void onGlobalLayout() {
//
//                ViewTreeObserver obs = tv.getViewTreeObserver();
//                obs.removeGlobalOnLayoutListener(this);
//                if (maxLine == 0) {
//                    int lineEndIndex = tv.getLayout().getLineEnd(0);
//                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
//                    tv.setText(text);
//                    tv.setMovementMethod(LinkMovementMethod.getInstance());
//                    tv.setText(
//                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
//                                    viewMore), TextView.BufferType.SPANNABLE);
//                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
//                    int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
//                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
//                    tv.setText(text);
//                    tv.setMovementMethod(LinkMovementMethod.getInstance());
//                    tv.setText(
//                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
//                                    viewMore), TextView.BufferType.SPANNABLE);
//                } else {
//                    int lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
//                    String text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
//                    tv.setText(text);
//                    tv.setMovementMethod(LinkMovementMethod.getInstance());
//                    tv.setText(
//                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
//                                    viewMore), TextView.BufferType.SPANNABLE);
//                }
//            }
//        });}
//
//    private static SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv,
//                                                                            final int maxLine, final String spanableText, final boolean viewMore) {
//        String str = strSpanned.toString();
//        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);
//
//        if (str.contains(spanableText)) {
//
//
//            ssb.setSpan(new MySpannable(false){
//                @Override
//                public void onClick(View widget) {
//                    if (viewMore) {
//                        tv.setLayoutParams(tv.getLayoutParams());
//                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
//                        tv.invalidate();
//                        makeTextViewResizable(tv, -1, "View Less", false);
//                    } else {
//                        tv.setLayoutParams(tv.getLayoutParams());
//                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
//                        tv.invalidate();
//                        makeTextViewResizable(tv, 3, "View More", true);
//                    }
//                }
//            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);
//
//        }
//        return ssb;
//
//    }


}